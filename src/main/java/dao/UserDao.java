package dao;

import domain.User;
import util.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private static boolean userTable_created = false;


    public static boolean createUSER_TABLE(){
        String sql = "CREATE TABLE users (" +
                " id int unsigned not null auto_increment," +
                " name varchar(50) not null default ''," +
                " email varchar (50) not null," +
                " password varchar(50) not null," +
                " PRIMARY KEY (id)" +
                ")";

        if(!userTable_created){
            try {
                DB.getConnection().getPreparedStatement(sql).execute();
                System.out.println("Base de dados criada com sucesso");
                return true;
            } catch (SQLException e) {
                System.out.println("Nao pode ser criada a base de dados.");
            }
        }
        return false;
    }

    public static User getUserByPassword(String password){
        String sql = "select * from users where password = ?";
        return getUserBy(password,sql);
    }

    public static User getUserByEmail(String email){
        String sql = "select * from users where email = ?";
        return getUserBy(email, sql);

    }

    private static User getUserBy(String parameter, String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DB.getConnection().getPreparedStatement(sql);
            preparedStatement.setString(1, parameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String user_email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User returned_user = User.builder().id(id).name(name).email(user_email).passaword(password).build();
                return returned_user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int save_user(User user){
        String sql = "insert into users (name, email, password) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DB.getConnection().getPreparedStatement(sql);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3, user.getPassaword());
            int execute = preparedStatement.executeUpdate();
            return execute;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }





}
