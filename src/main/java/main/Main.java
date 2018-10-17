package main;

import java.util.logging.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import accounts.AccountService;
import dbService.DBService;
import dbService.DBServiceImpl;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import servlets.WebSocketChatServlet;

public class Main {
    private static final String SIGNUP_PATH = "/signup";
    private static final String SIGNIN_PATH = "/signin";
    private static final String WEBSOCKET_PATH = "/chat";
    private static final String RESOURCE_BASE = "public_html";
    private static Logger log = Logger.getLogger(Main.class.getName());
        
    public static void main(String[] args) throws Exception {
        DBService dbService = new DBServiceImpl();
        dbService.printConnectInfo();

        AccountService accountService = new AccountService();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountService, dbService)), SIGNUP_PATH);
        context.addServlet(new ServletHolder(new SignInServlet(accountService, dbService)), SIGNIN_PATH);
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), WEBSOCKET_PATH);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(RESOURCE_BASE);

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlerList);
        server.start();
        log.info("Server started");
        server.join();
    }
}