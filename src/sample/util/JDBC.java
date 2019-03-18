package sample.util;

import java.sql.*;

public class JDBC {

    private final static String CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final static String URL = "jdbc:sqlserver://localhost;databaseName=PacmanMAP;integratedSecurity=true";
    private Connection connection;

    public Connection createConnection() {

        //loading driver class
        try {
            Class.forName(CLASS_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load the class!");
            System.exit(-1);
        }

        //get connection
        try {
            return connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("ERROR getting connection!");
        }

        return null;
    }
}
