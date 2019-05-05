package com.comtrade.threads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.Message;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static com.comtrade.domain.Constants.*;
import static java.nio.file.Files.write;


public class ClientThread extends Thread implements Serializable {
    private Socket socket;
    private String currentUsername;

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
                ControllerBLogic.getInstance().removeActiveUser(currentUsername);
                e.printStackTrace();
            }
        }
    }

    private void processClientRequest(TransferClass tf) throws IOException {
        switch (tf.getOperation()) {
            case SAVE_USER:
                User u = (User) tf.getClient_object();
                
                putUserInDataThread(u);
//                savePic(u); pomeri save pic u main, prvi ekran upload slike i namesta preference
                break;
            case RETURN_PROFILE:
                break;
            case CHECK_USER:
                User check = (User) tf.getClient_object();
                Map hashMap = ControllerBLogic.getInstance().getDatathread().getGetAllUserList();
                if (hashMap.containsKey(check.getUsername().toLowerCase())) {
                    TransferClass back = new TransferClass();
                    back.setOperation(USERNAME_TAKEN);
                    sendToClient(back);                }
                ControllerBLogic.getInstance().checkProfile(check); // ovo vrv ne treba
                break;
            case LIKE:
                System.out.println("hm");
//                Pictures p = (Pictures) tf.getClient_object();
//               byte[] by = p.getPicture();
//                File file = new File("pics/profilepics/ugabuga");
//
//                File theDirecc = new File("pics/profilepics/asd.jpg");
//                Path pathh = Paths.get(theDirecc.getAbsolutePath());
//                Path pathh2 = Paths.get(file.getAbsolutePath());
//                Files.createDirectories(pathh2);
//                System.out.println(pathh);
//                System.out.println(pathh2);
//                try {
//                   write(pathh, by);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                break;
            case DISLIKE:
                break;
            case SUPER:
                break;
        }
    }

    private void putUserInDataThread(User u) {
        ControllerBLogic.getInstance().getDatathread().getGetAllUserList().put(u.getUsername(), u);
        ControllerBLogic.getInstance().insertIntoActive(u.getUsername(), this);

    }

    private void savePics(User u) throws IOException {
        List<byte[]> bytes = u.getP().getPictures();
        int length = bytes.size();
        for(int i=0; i<length; i++){
            byte[] b = bytes.get(i);
            File theDir = new File(WINDIRPICS + u.getUsername());
            Path newDir = Paths.get(theDir.getAbsolutePath());
            Files.createDirectories(newDir);
            Path path = Paths.get(newDir + u.getUsername() + i + ".jpg");
            try {
                write(path, b);
            } catch (IOException e) {
                e.printStackTrace();
            }
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


    public void sentMsgToOnlineFriend(Message pm) {
        TransferClass tc = new TransferClass();
        tc.setServer_object(pm);
        sendToClient(tc);
    }


}
