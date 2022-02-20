    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghk1;

import Thuchanh1.MyConnection;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.*;
import java.beans.Statement;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Date;

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
                        connectSQL(port, dbName, username, password.trim());
                        dout.writeUTF("Connect to SQL success");
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
                            dout.writeUTF("Eror Login SQL "+ ex.getMessage());
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (Exception ex) {
                        try {
                            dout.writeUTF("Error Login SQL"+ ex.getMessage());
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
                        } catch (SQLException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case "LOAD_CAUHOI":{
                    try{
                        PreparedStatement ps = con.prepareStatement("SELECT * FROM thigk.bode order by RAND() limit 10 ");
                        ResultSet rs = ps.executeQuery();
                        while(rs.next()){
                           String data = String.format("%s·%s·%s·%s·%s·%s·%s·%s","data",
                                   rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7));
                           System.out.println("Data receive: " + data);
                           dout.writeUTF(data);
                        }
                        dout.writeUTF("null-end");
                        
                        rs.close();
                        ps.close();
                        
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
                }
                case "SAVE_DIEM":{
                    try{
                        String[] data = din.readUTF().split("-");
                        String maSinhVien = data[0];
                        String diem = data[1];
                        SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
                        Date currentTime = new Date();
                        String dayString = df.format(currentTime);
                        System.out.println(dayString);
                        PreparedStatement ps = con.prepareStatement(String.format("INSERT INTO diem VALUE('%s','%s','%s')", dayString,diem, maSinhVien));
                        ps.executeUpdate();
                        ps.close();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
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
