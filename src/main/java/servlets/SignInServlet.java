package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import practice.CreatedBy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CreatedBy(author = "Seggas", date = "01.04.18")

public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) { this.accountService = accountService; }

    public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        String login = rq.getParameter("login");
        String pass = rq.getParameter("password");
        rs.setContentType("text/html;charset=utf-8");

        if (login == null || pass == null) {
            rs.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);
        if (profile == null || !profile.getPass().equals(pass)) {
            rs.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            rs.getWriter().println("Unauthorized");
            return;
        } else {
            rs.setStatus(HttpServletResponse.SC_OK);
            rs.getWriter().println("Authorized: "+login);
        }

    }
}
