package main;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import practice.CreatedBy;
import servlets.SignUpServlet;

@CreatedBy(author = "Seggas", date = "01.04.18")

public class Main {

    public static void main(String[] args) throws DBException {
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        AccountService accountService = new AccountService();
        //accountService.addNewUser(new UserProfile("admin"), dbService);
        //accountService.addNewUser(new UserProfile("test"), dbService);

        try {
            long userId = dbService.addUser(new UserProfile("admin"));
            System.out.println("Added user Id: "+userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: "+dataSet);

        } catch (DBException e) {
            e.printStackTrace();
        }

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(accountService, dbService)), "api/v1/signUp");
    }
}