package com.comtrade.threads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.Message;
import com.comtrade.domain.Pictures;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;
import com.comtrade.util.ReadFolderUtil;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.comtrade.domain.Constants.*;
import static java.nio.file.Files.write;


public class ClientThread extends Thread implements Serializable {
    private Socket socket;
    private String currentUsername;
    private List<GeneralDomain> matches;


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
            case LOGIN:
                u = (User) tf.getClient_object();
                checkCredentials(u);

                break;
            case CHECK_USER:
                User check = (User) tf.getClient_object();
                Map hashMap = ControllerBLogic.getInstance().getDatathread().getGetAllUserList();
                TransferClass back = new TransferClass();

                if (hashMap.containsKey(check.getUsername().toLowerCase())) {
                    back.setOperation(USERNAME_TAKEN);
                    sendToClient(back);
                }else{
                    back.setOperation(USERNAME_OK);
                    sendToClient(back);
                }

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

    private void checkCredentials(User u) {
        Map hashMap = ControllerBLogic.getInstance().getDatathread().getGetAllUserList();
        HashMap<String, Object> loginMap = new HashMap<String, Object>();
        ReadFolderUtil readFolderUtil;
        Pictures p = new Pictures();
        TransferClass returnLogin = new TransferClass();
        if(hashMap.containsKey(u.getUsername())){
            User login = (User) hashMap.get(u.getUsername());
            login.getRating().setNewStatus(true);
            if(login.getPass().equals(u.getPass())){
                if(login.getRating().isNewStatus()){
//                    loginMap.put(login.getUsername(), login);
                    returnLogin.setOperation(LOGIN);
                    returnLogin.setServer_object(login);
                    sendToClient(returnLogin);
                    ControllerBLogic.getInstance().insertIntoActive(u.getUsername(), this);
                }
                //ovde treba da pakuje i slike + slike za sve matcheve
            }else{
                returnLogin.setOperation(WRONG_LOGIN);
                sendToClient(returnLogin);
            }
        }else{
            returnLogin.setOperation(WRONG_LOGIN);
            sendToClient(returnLogin);
        }


    }

    private void putUserInDataThread(User u) {

        ControllerBLogic.getInstance().getDatathread().getGetAllUserList().put(u.getUsername(), u);

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

    private void loadPics(){

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
        tc.setOperation(NEW_MESSAGE);
        tc.setServer_object(pm);
        sendToClient(tc);
    }





    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public List<GeneralDomain> getMatches() {
        return matches;
    }

    public void setMatches(List<GeneralDomain> matches) {
        this.matches = matches;
    }


}
