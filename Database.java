package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database implements IDB{

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {

        String connectionUrl = "jdbc:postgresql://localhost:5432/restaurant";

        try {
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "1234demo");

            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
