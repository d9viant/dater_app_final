package com.comtrade.threads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.threads.backupThreads.DataBackupThread;
import com.comtrade.transfer.TransferClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static com.comtrade.domain.Constants.*;


public class ClientThread extends Thread {
    private Path to;
    private Path from;

    private Socket socket;
    private DataBackupThread backupThread = new DataBackupThread();
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
                ControllerBLogic.getInstance().getUserFromDB(hm);  //gets user from db
                User backFromDB = (User) hm.get(USER);
                backupThread.getGetAllUserList().put(backFromDB.getUsername(), backFromDB);
                byte[] bytes = u.getPictures().getProfilePicture();
                Path path = Paths.get("src\\ProfilePics\\" + u.getUsername() + ".jpg");
                try {
                    Files.write(path, bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }

//
//                from = Paths.get(selectedFile.toURI());
//                to = Paths.get("src\\ProfilePics\\" + u.getUsername() + selectedFile.getName());
//                try {
//                    Files.copy(from, to);
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }


                break;
            case RETURN_PROFILE:
                break;
            case CHECK_USER:
                User check = (User) tf.getClient_object();
                HashMap hashMap = backupThread.getGetAllUserList();
                if (hashMap.containsKey(check.getUsername().toLowerCase())) {
                    TransferClass back = new TransferClass();
                    back.setOperation(USERNAME_TAKEN);
                    sendToClient(back);
                }
                ControllerBLogic.getInstance().checkProfile(check); // ovo vrv ne treba
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
