package main;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import practice.CreatedBy;
import servlets.*;

import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.logging.Logger;

/**
 * Created by seggas on 18.03.17.
 */
@CreatedBy(author = "Seggas", date = "18.03.17")
public class Main {
//    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
            long userId = dbService.addUser("Seggas");
            System.out.println("Added user Id: "+userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: "+dataSet);

//            dbService.cleanUp();
        } catch (DBException e) {
            e.printStackTrace();
        }

        //Class annotationClass = Main.class;
        //Method annotationMethod = annotationClass.getMethod("main");

        //CreatedBy methodAnn = annotationMethod.getAnnotation(CreatedBy.class);
        //CreatedBy classAnn = (CreatedBy) annotationClass.getAnnotation(CreatedBy.class);

        /*AllRequestServlet allRequestServlet = new AllRequestServlet();
        MirrorServlet mirrorServlet = new MirrorServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(mirrorServlet), "/mirror");*/
//        context.addServlet(new ServletHolder(allRequestServlet), "/*");

/*
        Server server = new Server(8080);
        server.setHandler(context);
*/

        /*AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //context.addServlet(new ServletHolder(new UsersServlet(accountService)), "api/v1/users");
        context.addServlet(new ServletHolder(new SessionServlet(accountService)), "/api/v1/sessions");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
        DriverManager.registerDriver(driver);
        String url =
            "jdbc:mysql://"+    // db type
            "localhost:"+       // host name
            "3306/"+            // port
            "db_example?"+      // db name
            "user=tully&"+      // login
            "password=tully";   // password
        Connection connection = DriverManager.getConnection(url.toString());

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        log.info("Server started");
        server.join();*/
    }
}