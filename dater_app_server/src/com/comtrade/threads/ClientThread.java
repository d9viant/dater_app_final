package com.comtrade.threads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.Pictures;
import com.comtrade.domain.User;
import com.comtrade.threads.backupThreads.DataBackupThread;
import com.comtrade.transfer.TransferClass;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static com.comtrade.domain.Constants.*;
import static java.nio.file.Files.write;


public class ClientThread extends Thread implements Serializable {
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

    private void processClientRequest(TransferClass tf) throws IOException {
        switch (tf.getOperation()) {
            case SAVE_USER:
                User u = (User) tf.getClient_object();
                saveUser(u);
                savePic(u);
                break;
            case RETURN_PROFILE:
                break;
            case CHECK_USER:
                User check = (User) tf.getClient_object();
                Map hashMap = backupThread.getGetAllUserList();
                if (hashMap.containsKey(check.getUsername().toLowerCase())) {
                    TransferClass back = new TransferClass();
                    back.setOperation(USERNAME_TAKEN);
                    sendToClient(back);
                }
                ControllerBLogic.getInstance().checkProfile(check); // ovo vrv ne treba
                break;
            case LIKE: //TESTIRAM KURCE OVDE
                System.out.println("hm");
                Pictures p = (Pictures) tf.getClient_object();
                byte[] by = p.getPicture();
                File file = new File("pics/profilepics/ugabuga");

                File theDirecc = new File("pics/profilepics/Kurac.jpg");
                Path pathh = Paths.get(theDirecc.getAbsolutePath());
                Path pathh2 = Paths.get(file.getAbsolutePath());
                Files.createDirectories(pathh2);
                System.out.println(pathh);
                System.out.println(pathh2);
                try {
                    write(pathh, by);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case DISLIKE:
                break;
            case SUPER:
                break;
        }
    }

    private void saveUser(User u) {
        HashMap<String, GeneralDomain> hm = new HashMap<>();
        hm.put(USER, u);
        ControllerBLogic.getInstance().saveIntoDB(hm);  //saves user to db
        ControllerBLogic.getInstance().getUserFromDB(hm);  //gets user from db
        User backFromDB = (User) hm.get(USER);
        backupThread.getGetAllUserList().put(backFromDB.getUsername(), backFromDB);

    }

    private void savePic(User u) throws IOException {
        byte[] bytes = u.getPictures().getPicture();
        File theDir = new File(WINDIRPICS + u.getUsername());
        Path newDir = Paths.get(theDir.getAbsolutePath());
        Files.createDirectories(newDir);
        Path path = Paths.get(newDir + u.getUsername() + ".jpg");
        try {
            write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
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
