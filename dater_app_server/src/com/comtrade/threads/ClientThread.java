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
                System.out.println(u.getUsername());
                System.out.println(u.getFirstName());
                System.out.println(u.getLastName());
                putUserInDataThread(u);
                break;
            case LOGIN:
                System.out.println("umri na loginu");
                User login = (User) tf.getClient_object();
                System.out.println(login.getUsername());
                checkCredentials(login);
                break;
            case CHECK_USER:
                System.out.println("umri");
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
                User test = (User) tf.getClient_object();
                savePics(test);
                break;
            case DISLIKE:
                break;
            case RETURN_PROFILE:
                System.out.println("test test test");
                break;
        }
    }

    private void checkCredentials(User u) {
        Map<String, GeneralDomain> getAllUserList = null;
        System.out.println("usao je u p,etodu");
        try {
            getAllUserList = ControllerBLogic.getInstance().getDatathread().getGetAllUserList();

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("ucitao hash mapu");
        Pictures p = new Pictures();
        TransferClass returnLogin = new TransferClass();
        User check = (User) getAllUserList.get("keseljs");
        System.out.println(check.getUsername() + "IZ HASH MAPE");
        if(getAllUserList.containsKey(u.getUsername())){
            User login = (User) getAllUserList.get(u.getUsername());
            System.out.println("usao je u prvi if");
            login.getRating().setNewStatus(true);
            if(login.getPass().equals(u.getPass())){
                if(login.getRating().isNewStatus()){
//                    loginMap.put(login.getUsername(), login);
                    System.out.println("usao je u drugi if");
                    returnLogin.setOperation(LOGIN);
                    returnLogin.setServer_object(login);
                    sendToClient(returnLogin);
                    System.out.println("poslat je login");
                    ControllerBLogic.getInstance().insertIntoActive(u.getUsername(), this);
                }
                returnLogin.setOperation(LOGIN);
                returnLogin.setServer_object(login);
                sendToClient(returnLogin);
            }else{

                returnLogin.setOperation(LOGIN);
                returnLogin.setServer_object(login);
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
