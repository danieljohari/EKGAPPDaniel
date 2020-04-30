package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TempDAOSQLImpl implements TempDAO {
    @Override
    public void save(TempDTO tempDTO) {
        Connection conn2 = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = conn2.prepareStatement("INSERT INTO TempMål(CPR, Temp,Tid) VALUES (?,?,?)");
            preparedStatement.setString(1,tempDTO.getCpr());
            preparedStatement.setDouble(2,tempDTO.getTemp());
            preparedStatement.setTimestamp(3,tempDTO.getTime());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
