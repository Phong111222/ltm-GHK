/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghk1;

import Thuchanh1.MyConnection;
import java.sql.*;
import java.beans.Statement;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Server extends Thread {

    private int port;
    private ServerSocket server;
    private Socket client;
    private String messageFromClient = "OnHold";
    private Statement stmt;
    DataInputStream din;
    DataOutputStream dout;
    Connection con;

    public Server(int port) {
        this.port = port;
    }

    public void init() {
        try {
            server = new ServerSocket(this.port);
            System.out.println("Server is running on port " + this.port);
            client = server.accept();
            din = new DataInputStream((client.getInputStream()));
            dout = new DataOutputStream(client.getOutputStream());
            JOptionPane.showMessageDialog(null, "Client connected !!");
            System.out.println("Client da ket noi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.server.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        this.init();
        while (true) {
            try {
                messageFromClient = din.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            switch (messageFromClient) {
                case "OnHold":
                    break;
                case "LoginSQL": {
                    try {
                        String username = din.readUTF();
                        String password = din.readUTF();
                        String port = din.readUTF();
                        String dbName = din.readUTF();
                        JOptionPane.showMessageDialog(null, String.format("%s-%s-%s-%s", username, password, dbName, port));
                        connectSQL(port, dbName, username, password.trim());
                        try {
                            PreparedStatement ps = con.prepareStatement("SELECT * FROM SinhVien ORDER BY maSinhVien DESC LIMIT 1;");
                            ResultSet rs = ps.executeQuery();
                            String result = "";
                            if (rs.next()) {
                                result = result + rs.getInt(1);
                            }
                            if (result.equals("")) {
                                result = "1";
                                System.out.println(result);
                                dout.writeUTF(result);
                            } else {
                                dout.writeUTF(result);
                            }
                        } catch (SQLException ex) {
                            dout.writeUTF("Loi con me m");
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (Exception ex) {
                        try {
                            dout.writeUTF("Loi r con di");
                        } catch (IOException ex1) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case "SAVE_INFO": {
                    try {
                        String hoten = din.readUTF();
                        String sdt = din.readUTF();
                        try {
                            PreparedStatement ps = con.prepareStatement(String.format("INSERT INTO SinhVien VALUES(NULL,'%s','%s')", hoten, sdt));
                            ps.executeUpdate();
                            dout.writeUTF("Luu sinh vien thanh cong");
                        } catch (SQLException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }

            }
        }
    }

    public void connectSQL(String port, String dbName, String username, String password) throws Exception {
        con = MyConnection.getConnection(port, dbName, username, password);
    }

    public static Connection getConnection(String port, String dbName, String username, String password) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String URL = String.format("jdbc:mysql://localhost:%s/%s?user=%s&password=%s", port, dbName, username, password);
        System.out.println(URL);
        Connection con = DriverManager.getConnection(URL);
        return con;
    }
}
