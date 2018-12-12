package servlets;

import accounts.AccountServiceI;
import javax.servlet.http.HttpServlet;

public class UsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AccountServiceI accountService;

    public UsersServlet(AccountServiceI accountService) { 
        this.accountService = accountService; 
    }
}
