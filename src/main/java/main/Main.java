package main;

import org.apache.logging.log4j.Logger;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.logging.log4j.LogManager;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import accounts.AccServiceController;
import accounts.AccServiceControllerMBean;
import accounts.AccountService;
import accounts.AccountServiceI;
import dbService.DBService;
import dbService.DBServiceImpl;
import servlets.AdminServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import servlets.WebSocketChatServlet;

public class Main {
    private static final String SIGNUP_PATH = "/signup";
    private static final String SIGNIN_PATH = "/signin";
    private static final String WEBSOCKET_PATH = "/chat";
    private static final String ADMIN_PATH = "/admin";
    private static final String RESOURCE_BASE = "public_html";
    
    private static final Logger log = LogManager.getLogger(Main.class.getName());
        
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            log.error("Use port as the first argument");
            System.exit(1);
        }

        int port = Integer.valueOf(args[0]);
        log.info("Starting at http://127.0.0.1:" + port);

        DBService dbService = new DBServiceImpl();
        dbService.printConnectInfo();

        AccountServiceI accountService = new AccountService(10);

        AccServiceControllerMBean serverStat = new AccServiceController(accountService);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=AccountServerController.usersLimit");
        mbs.registerMBean(serverStat, name);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountService, dbService)), SIGNUP_PATH);
        context.addServlet(new ServletHolder(new SignInServlet(accountService, dbService)), SIGNIN_PATH);
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), WEBSOCKET_PATH);
        context.addServlet(new ServletHolder(new AdminServlet(accountService)), ADMIN_PATH);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(RESOURCE_BASE);

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(port);
        server.setHandler(handlerList);
        server.start();
        log.info("Server started");
        server.join();
    }
}