package com.comtrade.communication;

import com.comtrade.transfer.TransferClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Comm {
    private static Comm instance;
    private Socket socket;


    private Comm(){
        try {
            socket = new Socket("127.0.0.1", 9000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Comm getInstance(){
        if(instance == null){
            instance = new Comm();
        }
        return instance;
    }

    public void send(TransferClass tf){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.writeObject(tf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TransferClass read() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        return  (TransferClass) ois.readObject();
    }


}
