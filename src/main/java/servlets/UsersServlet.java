package servlets;

import accounts.AccountService;
import javax.servlet.http.HttpServlet;

public class UsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AccountService accountService;

    public UsersServlet(AccountService accountService) { 
        this.accountService = accountService; 
    }
}
