/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghk1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Server extends Thread{
    private int port;
    private ServerSocket server;
    private Socket client;
    public Server(int port){
        this.port = port;
    }
    public void init(){
        try{
            server = new ServerSocket(this.port);
            System.out.println("Server is running on port "+this.port);
            client = server.accept();
            System.out.println("Client da ket noi");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void close(){
        try {
            this.server.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run(){
        this.init();
    }
    
}
