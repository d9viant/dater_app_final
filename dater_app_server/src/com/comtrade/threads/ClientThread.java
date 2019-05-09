package com.comtrade.threads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.*;
import com.comtrade.transfer.TransferClass;

import java.io.*;
import java.lang.Exception;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.comtrade.domain.Constants.*;
import static java.nio.file.Files.write;


public class ClientThread extends Thread implements Serializable {
    private Socket socket;
    private volatile String currentUsername;
    private List<GeneralDomain> matches;
    private Map<String, GeneralDomain> getAllUserList;


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
                Pictures p = new Pictures();
                User login = (User) tf.getClient_object();
                getAllUserList = ControllerBLogic.getInstance().getDatathread().getGetAllUserList();
                System.out.println("got login data");
                if(getAllUserList.containsKey(login.getUsername())){
                    User check = (User) getAllUserList.get(login.getUsername());
                    currentUsername=check.getUsername();
                    if(login.getPass().equals(check.getPass())){
                        Path newDir = Paths.get(SERVERPICS + login.getUsername()+"/folder");
                        Files.createDirectories(newDir);

                        Map<String, Object> testPicsforUser = getall(check);

                        try {
                            p = getPics(check);
                        }catch (Exception e){
                            System.out.println("nov user nema slike");
                        }
                        check.setP(p);
                        testPicsforUser.put("current", check);
//TODO TREBA DA KREIRA FOLDER PRE NEGO STO POKUSA DA POVUCE SLIKE U USTA GA JEBEM SKRBAVA
                        TransferClass ltf = new TransferClass();
                        ltf.setOperation(LOGIN);
                        ltf.setServer_object(testPicsforUser);
                        ControllerBLogic.getInstance().insertIntoActive(check.getUsername(), this);
                        sendToClient(ltf);

                    }
                }
                 if(!getAllUserList.containsKey(login.getUsername())){
                    TransferClass tce = new TransferClass();
                    tce.setOperation(WRONG_LOGIN);
                    sendToClient(tce);
                }
                break;
            case CHECK_USER:
                Map check = ControllerBLogic.getInstance().getDatathread().getGetAllUserList();
                User checkU = (User) tf.getClient_object();
                if(check.containsKey(checkU)){
                    TransferClass checktf = new TransferClass();
                    checktf.setOperation(USERNAME_TAKEN);
                    sendToClient(checktf);
                }
                break;

            case SAVEPICS:
                User pics = (User) tf.getClient_object();
                savePics(pics);
                break;

            case TEST:
                User test = (User) tf.getClient_object();
                Map testm = ControllerBLogic.getInstance().getDatathread().getGetAllUserList();
                testm.put(test.getUsername(), test);
                System.out.println();
                break;
            case UPDATE_RATING:
                Rating r = (Rating) tf.getClient_object();
                Map users = ControllerBLogic.getInstance().getDatathread().getGetAllUserList();
                User rating  = (User) users.get(r.getUsername());
                rating.setR(r);
                Map <String, ClientThread> active = ControllerBLogic.getInstance().getConnectedUserMap();
                for (Map.Entry<String, ClientThread> entry : active.entrySet()) {
                    if(entry.getKey().equals(r.getUsername())){
                        entry.getValue().updateRating(r);
                    }
                }
                break;
            case CREATE_MATCH:
                Matches create = (Matches) tf.getClient_object();
                Map<String, List<GeneralDomain>> matchesC=ControllerBLogic.getInstance().getDatathread().getAllMatches();
                List<GeneralDomain> lista = new ArrayList<>();
                matchesC.put(currentUsername, lista);
                matchesC.get(currentUsername).add(create);
                List<GeneralDomain> matchAdd = new ArrayList<>();
                matchesC.put(create.getRequestUsername(), matchAdd);
                matchesC.get(create.getRequestUsername()).add(create);

                Map <String, ClientThread> activeM = ControllerBLogic.getInstance().getConnectedUserMap();
                for (Map.Entry<String, ClientThread> entry : activeM.entrySet()) {
                    if(entry.getKey().equals(create.getRequestUsername())){
                        create.setRequestUsername(currentUsername);
                        entry.getValue().sendCreatedMatch(create);
                    }
                }
                break;
            case UPDATE_MATCH:
                Matches update = (Matches) tf.getClient_object();
                Map<String, List<GeneralDomain>> matches=ControllerBLogic.getInstance().getDatathread().getAllMatches();
                List<GeneralDomain> match= matches.get(update.getRequestUsername());
                for(GeneralDomain m: match){
                    Matches search = (Matches) m;
                    if(search.getUsernameOne().equals(currentUsername) || search.getUsernameTwo().equals(currentUsername)){
                        search.setMatchStatus(MATCHED);
                        search.setReadyForSql(RDYFORDB);
                    }
                }
                List<GeneralDomain> currmatch= matches.get(currentUsername);
                for(GeneralDomain curr: currmatch){
                    Matches search = (Matches) curr;
                    if(search.getUsernameOne().equals(update.getRequestUsername()) || search.getUsernameTwo().equals(update.getRequestUsername())){
                        search.setMatchStatus(MATCHED);
                        search.setReadyForSql(RDYFORDB);
                    }
                }
                Map <String, ClientThread> activeM2 = ControllerBLogic.getInstance().getConnectedUserMap();
                for (Map.Entry<String, ClientThread> entry : activeM2.entrySet()) {
                    if(entry.getKey().equals(update.getRequestUsername())){
                        update.setRequestUsername(currentUsername);
                        entry.getValue().updateMatches(update);
                    }
                }
                break;

        }
    }


    private Pictures getPics(User check) throws IOException {
        Pictures p = new Pictures();
        byte[] fileContent = new byte[0];
        List<File> list;
        File directory = new File("serverpics/"+check.getUsername());
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if ( (!file.isDirectory()) && (file.getAbsolutePath().endsWith(".jpg") )) {
                System.out.println(file.getName());
                fileContent=Files.readAllBytes(file.toPath());
                p.getPictures().add(fileContent);
            }
        }

        return p;
    }

    private Map getall(User check) throws IOException {
        Pictures p = new Pictures();
        int upRate = check.getRating().getRating() + 200;
        int downRate = check.getRating().getRating()-200;
        Map<String, Object> all = new HashMap<>();
        List<GeneralDomain> matches = ControllerBLogic.getInstance().getDatathread().getAllMatches().get(check.getUsername());
        List<GeneralDomain> messages = ControllerBLogic.getInstance().getDatathread().getAllMessages().get(check.getUsername());
        List<GeneralDomain> ratings = new ArrayList<>();
        all.put("messages", messages);
        all.put("matches", matches);



        for (Map.Entry<String, GeneralDomain> entry : getAllUserList.entrySet()) {{
            User u = (User) entry.getValue();
            if(!u.getUsername().equals(check.getUsername()) && u.getRating().getRating() <= upRate && u.getRating().getRating() >= downRate ){
                p = getPics(u);
                u.setP(p);
                ratings.add(u);
            }
        }}

        all.put("rating", ratings);

        return all;
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
        List<byte[]> pictures = u.getP().getPictures();
        int length = pictures.size();
        File theDir = new File(SERVERPICS);
        Path newDir = Paths.get(SERVERPICS + u.getUsername()+"/folder");
        Files.createDirectories(newDir);
        for(int i=0; i<length; i++){
            byte[] b = pictures.get(i);
            Path path = Paths.get(newDir + u.getUsername()+ i + ".jpg");
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


    public void sentMsgToOnlineMatch(Message pm) {
        TransferClass tc = new TransferClass();
        tc.setOperation(NEW_MESSAGE);
        tc.setServer_object(pm);
        sendToClient(tc);
    }

    public void updateRating(Rating r){
        TransferClass tc = new TransferClass();
        tc.setOperation(UPDATE_RATING);
        tc.setServer_object(r);
        sendToClient(tc);
    }

    public void updateMatches(Matches m){
        TransferClass tc = new TransferClass();
        tc.setOperation(UPDATE_MATCH);
        tc.setServer_object(m);
        sendToClient(tc);
    }

    private void sendCreatedMatch(Matches create) {
        TransferClass tc = new TransferClass();
        tc.setOperation(CREATE_MATCH);
        tc.setServer_object(create);
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
