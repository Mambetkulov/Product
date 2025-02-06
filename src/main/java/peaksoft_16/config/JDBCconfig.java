package peaksoft_16.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCconfig {

    public static Connection getConnection() {
        try{
            System.out.println("Connecting to database...");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/exam",
                    "postgres",
                    "muha2004"
            );

        }catch (SQLException e){
            System.out.println("something went wrong");
        }
        return null;
    }
}
