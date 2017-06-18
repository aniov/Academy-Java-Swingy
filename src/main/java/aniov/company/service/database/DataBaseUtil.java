package aniov.company.service.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by aniov on 6/18/2017.
 */
public class DataBaseUtil {

    private static final String USER_NAME = "rpguser";
    private static final String PASSWORD = "rpguser";
    private static final String SERVER = "jdbc:mysql://localhost:3306/rpg_db";

    public void connectToDb() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(SERVER, USER_NAME, PASSWORD);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println("Connection refused: " + e);
        } finally {
            if (connection != null){
                try {
                    connection.close();
                    System.out.println("Connection was closed successfully");
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e);
                }
            }
        }
    }
}
