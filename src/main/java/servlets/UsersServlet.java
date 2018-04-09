package servlets;

import accounts.AccountService;
import practice.CreatedBy;

import javax.servlet.http.HttpServlet;

@CreatedBy(author = "Seggas", date = "01.04.18")

public class UsersServlet extends HttpServlet {
    private final AccountService accountService;

    public UsersServlet(AccountService accountService) { this.accountService = accountService; }
}
