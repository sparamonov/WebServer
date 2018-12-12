package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import accounts.AccountServiceI;

public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LogManager.getLogger(AdminServlet.class.getName());
    private AccountServiceI accountService;

    public AdminServlet(AccountServiceI accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        int limit = accountService.getUsersLimit();
        int count = accountService.getUsersCount();
        log.info("AdminServlet.doGet Limit: {}. Count: {}", limit, count);

        rs.setContentType("text/html;charset=utf-8");
        rs.getWriter().println(limit);
        rs.setStatus(HttpServletResponse.SC_OK);
        return;
    }
}