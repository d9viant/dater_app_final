package com.comtrade.threads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.Message;
import com.comtrade.domain.Pictures;
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
        switch (tf.getOperation()){
            case SAVE_USER:
                User u = (User) tf.getClient_object();
                putUserInDataThread(u);
                break;

            case LOGIN_USER:
                User login = (User) tf.getClient_object();
                Map getAllUserList = ControllerBLogic.getInstance().getDatathread().getGetAllUserList();
                if(getAllUserList.containsKey(login.getUsername())){
                    User check = (User) getAllUserList.get(login.getUsername());
                    if(login.getPass().equals(check.getPass())){
                        Map loginMap = getMatchesAndPics(); // napravi matcheve i slike, stavi u hm i poteraj natrag
                        Map testPicsforUser = testPicsUser();
                        TransferClass ltf = new TransferClass();
                        ltf.setOperation(LOGIN);
                        ltf.setServer_object(check);
                        ControllerBLogic.getInstance().insertIntoActive(check.getUsername(), this);
                        sendToClient(ltf);

                    }
                }else if(!getAllUserList.containsKey(login.getUsername())){
                    TransferClass tce = new TransferClass();
                    tce.setOperation(WRONG_LOGIN);
                    sendToClient(tce);
                }
            case CHECK_USER:
                Map check = ControllerBLogic.getInstance().getDatathread().getGetAllUserList();
                User checkU = (User) tf.getClient_object();
                if(check.containsKey(checkU)){
                    TransferClass checktf = new TransferClass();
                    checktf.setOperation(USERNAME_TAKEN);
                    sendToClient(checktf);
                }


        }
    }

    private Map testPicsUser() {


        return null;
    }

    private Map getMatchesAndPics() {
        return null;
    }

    private void checkCredentials(User u) {
        Map getAllUserList = ControllerBLogic.getInstance().getDatathread().getGetAllUserList();
        Pictures p = new Pictures();
        TransferClass returnLogin = new TransferClass();
        if(getAllUserList.containsKey(u.getUsername())){
            User login = (User) getAllUserList.get(u.getUsername());
            if(login.getPass().equals(u.getPass())){
                TransferClass logged = new TransferClass();
                logged.setOperation(LOGIN_USER);
                logged.setServer_object(login);
                sendToClient(logged);

            }else{
                returnLogin.setOperation(WRONG_LOGIN);
                sendToClient(returnLogin);
            }
        }else if(!getAllUserList.containsKey(u.getUsername())){
            returnLogin.setOperation(WRONG_LOGIN);
            sendToClient(returnLogin);
        }


    }

    private void putUserInDataThread(User u) {
        ControllerBLogic.getInstance().getDatathread().getGetAllUserList().put(u.getUsername(), u);
        System.out.println("New User Added to Hash Map");
    }

    private void savePics(User u) throws IOException {
        List<byte[]> bytes = u.getP().getPictures();
        int length = bytes.size();
        for(int i=0; i<length; i++){
            byte[] b = bytes.get(i);
            File theDir = new File(SERVERPICS);
            Path newDir = Paths.get(theDir.getAbsolutePath());
            Files.createDirectories(newDir);
            Path path = Paths.get(theDir + u.getUsername() + i + ".jpg");
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
