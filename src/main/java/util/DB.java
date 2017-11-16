package util;

import domain.User;

import java.sql.*;

public class DB {

    // JDBC driver name and database URL
    private String DB_URL = "jdbc:mysql://localhost/login_storage";

    //  Database credentials
    private String USER = "root";
    private String PASS = "q1w2e3r4";

    private Connection conn;
    private static boolean database_created = false;

    private static DB instance;


    private DB() throws SQLException {
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        System.out.println("Connection establish with success...");
    }


    public static synchronized DB getConnection() throws SQLException {
        if(instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException{
        return instance.conn.prepareStatement(sql);
    }

    public ResultSet query(String query) throws SQLException{
        Statement statement = instance.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }

    public void closeConnection(){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public Connection getConn() {
        return conn;
    }
}
