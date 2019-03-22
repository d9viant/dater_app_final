package com.comtrade.threads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import static com.comtrade.domain.Constants.*;

public class ClientThread extends Thread {
    private Socket socket;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                TransferClass tf = (TransferClass) ois.readObject();
                processClientRequest(tf);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void processClientRequest(TransferClass tf) {
        switch (tf.getOperation()) {
            case SAVE_USER:
                User u = (User) tf.getClient_object();
                ControllerBLogic.getInstance().saveProfile(u);
                break;
            case RETURN_PROFILE:
                break;
            case CHECK_USER:
                Boolean check = (Boolean) tf.getClient_object();
                ControllerBLogic.getInstance().checkProfile(check);
                break;
            case LIKE:
                break;
            case DISLIKE:
                break;
            case SUPER:
                break;
        }
    }
}
