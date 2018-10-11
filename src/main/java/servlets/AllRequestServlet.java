package servlets;

import templater.PageGenerator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AllRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        Map<String, Object> pageVars = createPageVariablesMap(rq);
        pageVars.put("message", "");

        rs.getWriter().println(PageGenerator.instance().getPage("page.html", pageVars));

        rs.setContentType("text/html;charset=utf-8");
        rs.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        Map<String, Object> pageVars = createPageVariablesMap(rq);

        String mes = rq.getParameter("message");

        rs.setContentType("text/html;charset=utf-8");

        if (mes == null || mes.isEmpty())
            rs.setStatus(HttpServletResponse.SC_FORBIDDEN);
        else
            rs.setStatus(HttpServletResponse.SC_OK);

        pageVars.put("message", mes == null ? "" : mes);

        rs.getWriter().println(PageGenerator.instance().getPage("page.html", pageVars));
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest rq) {
        Map<String, Object> pageVars = new HashMap<>();
        pageVars.put("method", rq.getMethod());
        pageVars.put("URL", rq.getRequestURL().toString());
        pageVars.put("pathInfo", rq.getPathInfo());
        pageVars.put("sessionId", rq.getSession().getId());
        pageVars.put("params", rq.getParameterMap().toString());
        return pageVars;
    }
}
