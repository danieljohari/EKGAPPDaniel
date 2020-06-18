package data;

import javafx.application.Platform;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EkgDAOSQLImpl implements EkgDAO {

    @Override
    public List<EKGDTO> load(String cpr) {
        return null;
    }
@Override
    public void savebatch(LinkedList<EKGDTO> ekgBatch) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection connection = Connector.getConnection();
                PreparedStatement preparedStatement = null;
                try {
                    connection.setAutoCommit(false);
                    preparedStatement = connection.prepareStatement("INSERT INTO EKGMål (CPR, EKGVolt, Tid) VALUES (?,?,?) ");
                    for (EKGDTO ekgDTO : ekgBatch) {
                        preparedStatement.setString(1, ekgDTO.getCpr());
                        preparedStatement.setDouble(2, ekgDTO.getEkg());
                        preparedStatement.setTimestamp(3, ekgDTO.getTime());
                        preparedStatement.addBatch();
                        preparedStatement.executeBatch();

                        int[] updateCounts = preparedStatement.executeBatch();
                       // System.out.println(Arrays.toString(updateCounts));
                        connection.commit();

                    }
                    connection.setAutoCommit(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

        }).start();


    /*
    public List<EKGDTO> load(String cpr) {
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement("SELECT * FROM ");
            preparedStatement.setString(1,cpr);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<EKGDTO> listEkg = mapResultSetToDTOList(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private List<EKGDTO> mapResultSetToDTOList(ResultSet resultSet) throws SQLException {
        List<EKGDTO> listEkg = new LinkedList<>();
            while (resultSet.next()) {
                EKGDTO ekgdto = new EKGDTO();
                ekgdto.setCpr(resultSet.getString("CPR"));
                ekgdto.setEkg(resultSet.getDouble("EKGVolt"));
                ekgdto.setTime(resultSet.getTimestamp("Tid"));
                listEkg.add(ekgdto);
            }
        return listEkg;

     */
    }


}


