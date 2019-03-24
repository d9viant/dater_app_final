package com.comtrade.threads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;

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
                HashMap<String, GeneralDomain> userMap = new HashMap<>();
                userMap.put("user", u);
                userMap.put("age", u.getAge());
                userMap.put("gender", u.getGender());
                userMap.put("location", u.getLocation());
                userMap.put("ratings", u.getRating());
                ControllerBLogic.getInstance().saveIntoDB(userMap);
                break;
            case RETURN_PROFILE:
                break;
            case CHECK_USER:
                User check = (User) tf.getClient_object();
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
