package com.comtrade.threads;

import com.comtrade.domain.Exception;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server extends Thread{
    private TextArea txtServerLogs;

    public Server(TextArea txtServerLogs) {
        this.txtServerLogs=txtServerLogs;
    }

    public void run(){
        try {
            startServer();
            System.out.println("yes");
        } catch (IOException e) {
            try {
                throw new Exception("Server did not start");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void startServer() throws IOException {
        ServerSocket ss = new ServerSocket(9000);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        txtServerLogs.appendText("Server Started" + " " + "at" + " " + sdf.format(new Date()));
        while(true){
            Socket socket = ss.accept();
            ClientThread ct = new ClientThread();
            ct.setSocket(socket);
            ct.start();

        }

    }
}
