package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import accounts.AccountService;
import dbService.DBService;
import servlets.SignInServlet;
import servlets.SignUpServlet;

public class Main {
    private static final String SIGNUP_PATH = "/signup";
    private static final String SIGNIN_PATH = "/signin";
    private static final String RESOURCE_BASE = "public_html";
        
    public static void main(String[] args) throws Exception {
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        AccountService accountService = new AccountService();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountService, dbService)), SIGNUP_PATH);
        context.addServlet(new ServletHolder(new SignInServlet(accountService, dbService)), SIGNIN_PATH);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(RESOURCE_BASE);

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlerList);
        server.start();
        server.join();
    }
}