package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static Connection connection;

    private Connector() {

    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:mysql://db.diplomportal.dk/s195505?"
                        + "user=s195505&password=EzdGZT0jmPfiiN4KTPAlp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}




