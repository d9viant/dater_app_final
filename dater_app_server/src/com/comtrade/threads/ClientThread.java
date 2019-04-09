package com.comtrade.threads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.threads.backupThreads.FromDBBackupThread;
import com.comtrade.transfer.TransferClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import static com.comtrade.domain.Constants.*;

public class ClientThread extends Thread {
    private Socket socket;
    private FromDBBackupThread backupThread = new FromDBBackupThread();
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
                HashMap<String, GeneralDomain> hm = new HashMap<>();
                hm.put(USER, u);

                ControllerBLogic.getInstance().saveIntoDB(hm);  //saves user to db

                ControllerBLogic.getInstance().getUserFromDB(hm);  //gets user from db with unique ID
                User backFromDB = (User) hm.get(USER);
                backupThread.getGetAllUserList().get(ALL_USERS).put(backFromDB.getUsername(), backFromDB);
                break;
            case RETURN_PROFILE:
                break;
            case CHECK_USER:
                User check = (User) tf.getClient_object();
                HashMap hashMap = backupThread.getGetAllUserList().get(ALL_USERS);
                if (hashMap.containsKey(check.getUsername().toLowerCase())) {
                    TransferClass back = new TransferClass();
                    back.setOperation(USERNAME_TAKEN);
                    sendToClient(back);
                }
                ControllerBLogic.getInstance().checkProfile(check);

                break;
            case LIKE:
                System.out.println("KURAC");
                break;
            case DISLIKE:
                break;
            case SUPER:
                break;
        }
    }

    public void sendToClient(TransferClass tc2) {
        try {
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.writeObject(tc2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
