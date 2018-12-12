package servlets;

import accounts.AccountServiceI;
import accounts.UserProfile;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AccountServiceI accountService;

    public SessionServlet(AccountServiceI accountService) { this.accountService = accountService; }

    // get logged user profile
    public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        String sessionId = rq.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        if (profile == null) {
            rs.setContentType("text/html;charset=utf-8");
            rs.getWriter().println("UNAUTHORIZED");
            rs.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(profile);
            rs.setContentType("text/html;charset=utf-8");
            rs.getWriter().println(json);
            rs.setStatus(HttpServletResponse.SC_OK);
        }
    }

    // sign in
    public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        String login = rq.getParameter("login");
        String pass = rq.getParameter("pass");

        if (login == null || pass == null) {
            rs.setContentType("text/html;charset=utf-8");
            rs.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);
        if (profile == null || !profile.getPass().equals(pass)) {
            rs.setContentType("text/html; charset=utf-8");
            rs.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        accountService.addSession(rq.getSession().getId(), profile);
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        rs.setContentType("text/html;charset=utf-8");
        rs.getWriter().println(json);
        rs.setStatus(HttpServletResponse.SC_OK);
    }

    // sign out
    public void doDelete(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        String sessionId = rq.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        if (profile == null) {
            rs.setContentType("text/html;charset=utf-8");
            rs.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            accountService.deleteSession(sessionId);
            rs.setContentType("text/html;charset=utf-8");
            rs.getWriter().println("Goodbye");
            rs.setStatus(HttpServletResponse.SC_OK);
        }
    }
}