package servlets;

import accounts.AccountServiceI;
import accounts.UserProfile;
import dbService.DBException;
import dbService.DBService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AccountServiceI accountService;
    private final DBService dbService;
    
    public SignUpServlet(AccountServiceI accountService, DBService dbService) { 
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

        UserProfile profile = new UserProfile(login, pass, login);
        try {
            accountService.addNewUser(profile, dbService);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
