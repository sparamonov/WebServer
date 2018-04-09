package main;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import practice.CreatedBy;

@CreatedBy(author = "Seggas", date = "01.04.18")

public class Main {

    public static void main(String[] args) throws DBException {
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
            long userId = dbService.addUser("Seggas");
            System.out.println("Added user Id: "+userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: "+dataSet);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}