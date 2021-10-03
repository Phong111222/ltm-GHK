package Thuchanh1;

import java.sql.*;
import javax.swing.*;

public class MyConnection {

    public static Connection getConnection(String port, String dbName, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String URL = String.format("jdbc:mysql://localhost:%s/%s?user=%s&password=%s",port,dbName,username,password);
            System.out.println(URL);
            Connection con = DriverManager.getConnection(URL);
            return con;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
