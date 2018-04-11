package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBService;
import practice.CreatedBy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CreatedBy(author = "Seggas", date = "01.04.18")

public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;
    private final DBService dbService;

    public SignUpServlet(AccountService accountService, DBService dbService) {
        this.accountService = accountService;
        this.dbService = dbService;
    }

    public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        String login = rq.getParameter("login");
        String pass = rq.getParameter("password");
        rs.setContentType("text/html;charset=utf-8");

        if (login == null || pass == null) {
            rs.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = new UserProfile(login, pass, pass);
        accountService.addNewUser(profile, dbService);
    }
}
