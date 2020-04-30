package data;

import java.sql.*;


public class DBTester {


    private static BpmListener BPMSim;
    private static TempListener TempSim;
    private static SPO2Listener SPO2Sim;

    public static void main(String[] args) {

        Connection connection = Connector.getConnection();
        try {
            //Scanner scanner = new Scanner(System.in);
           // System.out.println("skriv en temp;");
          //  String Temp = scanner.next();
            Statement statement = connection.createStatement();
            ResultSet show_tables = statement.executeQuery("SELECT * FROM TempMål WHERE temp > 39");

            System.out.println("got a result with nr of columns");
            System.out.println(show_tables.getMetaData().getColumnCount());
            while (show_tables.next()){
                System.out.println("TempId: " + show_tables.getString(1));
                System.out.println("CPR: " + show_tables.getString(2));
                System.out.println("Temp: " + show_tables.getString(3));
                System.out.println(" ");
                }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
