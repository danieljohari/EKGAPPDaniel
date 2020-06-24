package data;

import javafx.application.Platform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BpmDAOSQLImpl implements BpmDAO {
    public void save(BpmDTO bpmDTO) {


                Connection conn1 = Connector.getConnection();
                try {
                    PreparedStatement preparedStatement = conn1.prepareStatement("INSERT INTO BPMMål(CPR, BPM,Tid) VALUES (?,?,?)");
                    preparedStatement.setString(1,bpmDTO.getCpr());
                    preparedStatement.setDouble(2,bpmDTO.getBpm());
                    preparedStatement.setTimestamp(3,bpmDTO.getTime());
                    preparedStatement.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

    }
/*
    @Override
    public void save(EKGDTO ekgdto) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn1 = Connector.getConnection();
                try {
                    PreparedStatement preparedStatement = conn1.prepareStatement("INSERT INTO BPMMål(CPR, BPM,Tid) VALUES (?,?,?)");
                    preparedStatement.setString(1,ekgdto.getCpr());
                    preparedStatement.setDouble(2,ekgdto.getBpm());
                    preparedStatement.setTimestamp(3,ekgdto.getTime());
                    preparedStatement.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
            }

 */



