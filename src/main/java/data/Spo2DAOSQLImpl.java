package data;

import javafx.application.Platform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Spo2DAOSQLImpl implements Spo2DAO {
    @Override
    public void save(Spo2DTO spo2DTO) {

            Connection conn = Connector.getConnection();
            try {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO SPO2Mål(CPR, SPO2,Tid) VALUES (?,?,?)");
                preparedStatement.setString(1, spo2DTO.getCpr());
                preparedStatement.setDouble(2, spo2DTO.getSpo2());
                preparedStatement.setTimestamp(3, spo2DTO.getTime());
                preparedStatement.execute();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
    }
/*
        Connection conn = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO MåleData(CPR, SPO2,Tid) VALUES (?,?,?)");
            preparedStatement.setString(1,spo2DTO.getCpr());
            preparedStatement.setDouble(2,spo2DTO.getSpo2());
            preparedStatement.setTimestamp(3,spo2DTO.getTime());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

 */



