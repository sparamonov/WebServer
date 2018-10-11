package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AccountService accountService;

    public SignInServlet(AccountService accountService, DBService dbService) { this.accountService = accountService; }

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
