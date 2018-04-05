package servlets;

import accounts.AccountService;

import javax.servlet.http.HttpServlet;

/**
 * Created by seggas on 12.04.17.
 */
public class UsersServlet extends HttpServlet {
    private final AccountService accountService;

    public UsersServlet(AccountService accountService) { this.accountService = accountService; }
}
