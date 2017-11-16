package dao;

import domain.UserRequest;
import util.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public boolean checkIfPasswordExists(UserRequest request){
        String sql = "select * from login where password = ?";
        try {
            PreparedStatement preparedStatement = DB.getConnection().getPreparedStatement(sql);
            preparedStatement.setString(1, request.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
