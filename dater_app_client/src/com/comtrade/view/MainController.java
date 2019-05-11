package com.comtrade.view;

import com.comtrade.compression.Compression;
import com.comtrade.controllerUI.Controller;
import com.comtrade.domain.*;
import com.comtrade.geoloc.GeoLoc;
import com.comtrade.transfer.TransferClass;
import com.jfoenix.controls.*;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapType;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;


import java.io.*;
import java.lang.Exception;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;

import static com.comtrade.domain.Constants.*;
import static java.nio.file.Files.write;

public class MainController implements Initializable, Serializable {

    @FXML
    private AnchorPane chattingWindow;

    @FXML
    private TextArea chatWindowText;

    @FXML
    private TextField sendTextmsg;

    @FXML
    private JFXButton sendTextGo;


    @FXML
    private JFXCheckBox boostCheck;


    @FXML
    private JFXSlider distanceSlider;

    @FXML
    private AnchorPane mapPane;

    @FXML
    private MapView mapView;

    @FXML
    private AnchorPane rootPane, settingsPane, chatPane;

    @FXML
    private ImageView imgMatchProfile, imgChangeProfile;

    @FXML
    private Label lvlNameAge;

    @FXML
    private TextArea bioTextFieldOpacity;

    @FXML
    private Label txtBiography;

    @FXML
    private JFXBadge testBadge;

    @FXML
    private JFXButton btnUpdtBio, enter, btnDislike, btnHeart, btnProfileToMain, btnStalkerGlobe, drawerProfile, drawerChat, drawerSettings, btnStalkerToMain, btnNadji, settingsBackToMain, btnChangeProf, backFromChatToMain;

    @FXML
    private JFXHamburger drawerImg;

    @FXML
    private AnchorPane profilePane;

    @FXML
    private Label lvlNameAge1;

    @FXML
    private TextArea txtBiography1;

    @FXML
    private JFXListView<Label> chatUserList;

    @FXML
    private ImageView imgStalkerGlobe, profilePic;

    @FXML
    private AnchorPane opacityPane, firstPane;

    @FXML
    private AnchorPane placeHolderPane;

    @FXML
    private AnchorPane drawerPane;

    @FXML
    private Button badgeButton;

    @FXML
    private JFXPopup popup = new JFXPopup();
    private Coordinate belgrade = new Coordinate(44.7866, 20.4489);

    @FXML
    private JFXButton backToChatPane;

    public static User getCurrentUser() {
        return currentUser;
    }

    private GeoLoc g = null;

    private static ArrayList<Double> red = new ArrayList<>(2);

    private static User currentUser;
    private User matchUser;

    private List<File> currentUserPhotos = new ArrayList<>();
    private List<File> otherUserPhotos = new ArrayList<>();
    private static List<GeneralDomain> matches;
    private static List<GeneralDomain> messages;
    private List<GeneralDomain> ratings;
    private List<User> preferreMatches = new ArrayList<>();

    private int iterator = 0;
    Map<String, Object> testPicsforUser;
    String item ;


    public void setCurrentUser(User currentUser) {
        MainController.currentUser = currentUser;
    }


    private final FileChooser fileChooser = new FileChooser();

    private int incrementSelection = 0;

    private boolean matchOrUser = true;
    private boolean chatBool = true;

    private String currentlyChatting;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialSetup();
        controlButtons();
        startGeoLoc();

    }

    private void startGeoLoc() {
        try {
            g = new GeoLoc();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initialSetup() {
        setPaneOut(chatPane, placeHolderPane);
        setPaneOut(opacityPane, placeHolderPane);
        setPaneOut(drawerPane, placeHolderPane);
        setPaneOut(profilePane, placeHolderPane);
        setPaneOut(mapPane, placeHolderPane);
        setPaneOut(chattingWindow, placeHolderPane);
        imgStalkerGlobe.setDisable(true);
        btnStalkerGlobe.setDisable(true);

    }

    private void controlButtons() {



        sendTextGo.setOnAction(Event->{

            sendtext();
        });

        sendTextmsg.setOnKeyPressed(Keyvent ->{
            if (Keyvent.getCode().equals(KeyCode.ENTER))
            {
                sendtext();
            }

        });



        backToChatPane.setOnAction(Event->{
            this.chatWindowText.clear();
            setPaneOut(chattingWindow, placeHolderPane);

        });


        chatUserList.setOnMouseClicked(Event ->{
            item = chatUserList.getSelectionModel().getSelectedItem().getText();
            messageOperations(item);
            setPaneIn(chattingWindow);


        });


        imgMatchProfile.setOnMouseClicked(Event -> {
            matchOrUser = false;
            loadPics(matchUser);
            setPaneIn(profilePane);
            try {
                setProfilePic(imgMatchProfile);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });


        btnHeart.setOnAction(Event -> {
            try {
                matchOperations(true);// Create or update match
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            cycleNextUser();
        });

        btnDislike.setOnAction(Event ->{
            try {
                matchOperations(false);// Create or update match
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            cycleNextUser();
        });




        btnUpdtBio.setOnAction(Event -> {
            String bio = bioTextFieldOpacity.getText();
            currentUser.setBio(bio);

        });

        enter.setOnAction(Event -> {
            if (boostCheck.isSelected()) {
                imgStalkerGlobe.setDisable(false);
                btnStalkerGlobe.setDisable(false);
            }
            currentUser = (User) getTestPicsforUser().get("current");
            ratings = (List<GeneralDomain>) getTestPicsforUser().get("rating");
            MainController.matches = (List<GeneralDomain>) getTestPicsforUser().get("matches");
            MainController.messages = (List<GeneralDomain>) getTestPicsforUser().get("messages");
            if(messages==null){
                messages = new ArrayList<>();
            }
            if(matches==null){
                matches = new ArrayList<>();
            }

            setPaneOut(firstPane, placeHolderPane);
            try {
                savePics(currentUser);
                for (int i = 0; i < ratings.size(); i++) {
                    User match = (User) ratings.get(i);
                    savePics(match);
                }

                loadPics(currentUser);
//                cycleImage(imgChangeProfile);


            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        imgChangeProfile.setOnMouseClicked(Event -> {
            try {

                setProfilePic(imgChangeProfile);
                cycleImage(imgChangeProfile);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });


        drawerChat.setOnAction(Event -> {
            try {
                checkMatchesUpdateBadges(true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            setPaneIn(chatPane);
            setPaneOut(opacityPane, drawerPane);
        });


        backFromChatToMain.setOnAction(Event -> {
            setPaneOut(chatPane, placeHolderPane);
        });

        settingsBackToMain.setOnAction(Event -> {
            setupOperations();

        });

        opacityPane.setOnMouseClicked(Event -> setPaneOut(opacityPane, drawerPane));

        btnProfileToMain.setOnAction(Event -> setPaneOut(profilePane, placeHolderPane));

        drawerImg.setOnMouseClicked(Event -> setPaneIn(drawerPane));

        drawerSettings.setOnAction(Event -> {
            setPaneIn(settingsPane);
            setPaneOut(opacityPane, drawerPane);
        });

        btnNadji.setOnAction(Event -> {
            Coordinate matchCoord = new Coordinate(matchUser.getLocation().getLatitude(), matchUser.getLocation().getLongitude());
            Marker matchMarker = Marker.createProvided(Marker.Provided.BLUE).setPosition(matchCoord).setVisible(true);
            mapView.setCenter(matchCoord);
            mapView.addMarker(matchMarker);
        });

        btnStalkerToMain.setOnAction(Event -> {
            setPaneOut(mapPane, placeHolderPane);
            MapViewClose();
        });

        btnStalkerGlobe.setOnAction(Event -> {
            MapView();
            setPaneIn(mapPane);
        });

        profilePic.setOnMouseClicked(Event -> {
            try {
                cycleImage(profilePic);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });

        drawerProfile.setOnAction(Event -> {
            try {
                matchOrUser = true;
                setProfilePic(profilePic);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            setPaneIn(profilePane);
            setPaneOut(opacityPane, drawerPane);
        });


        btnChangeProf.setOnAction(Event -> {
            TransferClass tf = new TransferClass();
            Compression compress = new Compression();
            File compressedimage;
            byte[] fileContent;
            Pictures p = new Pictures();
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("PICTURE files (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg");
            fileChooser.getExtensionFilters().add(extFilter);
            currentUserPhotos = fileChooser.showOpenMultipleDialog(null);
            if (currentUserPhotos != null) {
                String photoUrl = null;
                for (int i = 0; i < currentUserPhotos.size(); i++) {
                    File fi = currentUserPhotos.get(i);
                    try {
                        photoUrl = currentUserPhotos.get(0).toURI().toURL().toString();
                        compressedimage = compress.compression(fi);
                        fileContent = Files.readAllBytes(compressedimage.toPath());
                        p.getPictures().add(fileContent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Image image = new Image(photoUrl, 360, 360, true, true);
                imgChangeProfile.setImage(image);

            }

            tf.setOperation(SAVEPICS);
            currentUser.setP(p);
            tf.setClient_object(currentUser);
            Controller.getInstance().sendToServer(tf);
            try {
                savePics(currentUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void sendtext() {
        String text = sendTextmsg.getText();
        Message m = new Message();
        m.setUserOneId(currentUser.getUsername());
        m.setUserTwoId(item);
        System.out.println(item);
        m.setMessageStatus(UNREAD);
        m.setMessageBody(text);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        m.setMessagetime(timestamp);
        TransferClass msg = new TransferClass();
        m.setReadyForSql(RDYFORDB);
        m.setSenderId(currentUser.getUsername());
        msg.setClient_object(m);
        msg.setOperation(SEND_MESSAGE);
        messages.add(m);
        Controller.getInstance().sendToServer(msg);
        int hour = m.getMessagetime().toLocalDateTime().getHour();
        int minute = m.getMessagetime().toLocalDateTime().getMinute();
        this.chatWindowText.appendText("\n" +hour + ":"+ minute +"\n" + currentUser.getUsername() + " : " + m.getMessageBody());
        sendTextmsg.clear();
    }

    public void messageOperations(String username) {
        List<Message> ourList = new ArrayList<>();
        if(messages!=null){
            for(GeneralDomain message: messages){
                Message m = (Message) message;
                if(m.getUserOneId().equals(username) || m.getUserTwoId().equals(currentUser.getUsername()) && m.getUserOneId().equals(currentUser.getUsername()) || m.getUserTwoId().equals(username)){
                    ourList.add(m);
                }
            }
        }
            for(Message m : ourList){
                int hour = m.getMessagetime().toLocalDateTime().getHour();
                int minute = m.getMessagetime().toLocalDateTime().getMinute();
                Platform.runLater(() -> {
                    this.chatWindowText.appendText("\n" +hour + ":"+ minute +"\n" + username + " : " +  m.getMessageBody());
                });

            }


    }




    private void cycleNextUser() {
        if (iterator == preferreMatches.size()-1) {
            iterator = 0;
        }else{
            iterator++;
            matchUser = preferreMatches.get(iterator);
        }
        try {
            savePics(matchUser);
            loadPics(matchUser);
            setProfilePic(imgMatchProfile);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        lvlNameAge.setText(matchUser.getFirstName() + " " + matchUser.getAge().getAge());
        txtBiography.setText(matchUser.getBio());


    }

    private void noMoreUsersWarning() {
        Alert passAlert = new Alert(Alert.AlertType.WARNING);
        passAlert.setHeaderText(null);
        passAlert.setTitle("End of list!");
        passAlert.setContentText("Try Again Later :)");
        passAlert.showAndWait();


    }

    private void setupOperations() {
        if ( currentUser.getBio() == null) {
            Alert passAlert = new Alert(Alert.AlertType.WARNING);
            passAlert.setHeaderText(null);
            passAlert.setTitle("Info is missing");
            passAlert.setContentText("Add some pictures and write something about yourself :)");
            passAlert.showAndWait();
        } else {
            setPaneOut(settingsPane, placeHolderPane);
            int prefdist = (int) distanceSlider.getValue();
            currentUser.getLocation().setPrefferedDistance(prefdist);
            try {
                setupMatches();
                checkMatchesUpdateBadges(true);
            } catch (MalformedURLException e) {


            } catch (Exception e) {
                noMoreUsersWarning();
            }
        }
    }

    private void matchOperations(boolean b) throws FileNotFoundException {
        boolean check = false;
        matchUser.getRating().EloRating(currentUser.getRating().getRating(), b);
        matchUser.getRating().setReadyForSql(RDYFORDB);
        matchUser.getRating().setUsername(matchUser.getUsername());
        TransferClass tf = new TransferClass();
        tf.setClient_object(matchUser.getRating());
        tf.setOperation(UPDATE_RATING);
        Controller.getInstance().sendToServer(tf);
        if (matches.isEmpty()) {
            check = true;
        } else if (!check) {
            for (GeneralDomain match : matches) {
                Matches matches = (Matches) match;
                if (matches.getUsernameOne().equals(matchUser.getUsername()) || matches.getUsernameTwo().equals(matchUser.getUsername()) && matches.getMatchStatus() == 0) {
                    matches.setMatchStatus(MATCHED);
                    matches.setRequestUsername(matchUser.getUsername());
                    matches.setReadyForSql(RDYFORDB);
                    TransferClass mtf = new TransferClass();
                    check = false;
                    mtf.setOperation(UPDATE_MATCH);
                    mtf.setClient_object(matches);
                    Controller.getInstance().sendToServer(mtf);
                    try {
                        checkMatchesUpdateBadges(true);
                    } catch (FileNotFoundException e) {
                        System.out.println("no matches");
                    }

                } else {
                    check = true;
                }
            }
        }
        if (check) {
            Matches m = new Matches();
            m.setUsernameOne(currentUser.getUsername());
            m.setUsernameTwo(matchUser.getUsername());
            m.setRequestUsername(matchUser.getUsername());
            m.setReadyForSql(RDYFORDB);
            m.setMatchStatus(0);
            matches = new ArrayList<>();
            matches.add(m);
            TransferClass matchtc = new TransferClass();
            matchtc.setOperation(CREATE_MATCH);
            matchtc.setClient_object(m);
            Controller.getInstance().sendToServer(matchtc);
        }
        checkMatchesUpdateBadges(true);
    }

    private void setupMatches() throws MalformedURLException {
        for (GeneralDomain gd : ratings) {
            User u = (User) gd;
            preferreMatches.add(u);
//            for(GeneralDomain m : matches){
//                Matches mm = (Matches) m;
//                if(u.getUsername().equals(mm.getUsernameOne()) || u.getUsername().equals(mm.getUsernameTwo()) && mm.getMatchStatus()== 0){
//                    int distnce = (int) DistanceCalculator.getDistanceInBetween(u, currentUser);
//                    if (distnce <= currentUser.getLocation().getPrefferedDistance()) {
//                       preferreMatches.add(u);
//                }
//            }
//
//            }
        }
        loadPics(preferreMatches.get(0));
        matchUser = preferreMatches.get(0);
        setProfilePic(imgMatchProfile);
        lvlNameAge.setText(preferreMatches.get(0).getFirstName() + " " + preferreMatches.get(0).getAge().getAge());
        txtBiography.setText(preferreMatches.get(0).getBio());
    }

    public void checkMatchesUpdateBadges(Boolean check) throws FileNotFoundException {
        while (check) {
            for (GeneralDomain match : matches) {
                Matches m = (Matches) match;
                if (m.getMatchListed() == MATCH_UNLISTED && m.getMatchStatus() == MATCHED) {
                    if (m.getUsernameOne().equals(currentUser.getUsername())) {
                        Label lbl = new Label(m.getUsernameTwo());

                        lbl.setGraphic(new ImageView((new Image(new FileInputStream("/home/strahinja/IdeaProjects/dater_app_final/dater_app_client/src/assets/chatHeart.png")))));
                        chatUserList.getItems().add(lbl);
                        m.setMatchListed(MATCH_LISTED);
                    } else {
                        Label lbl = new Label(m.getUsernameOne());
                        lbl.setGraphic(new ImageView((new Image(new FileInputStream("/home/strahinja/IdeaProjects/dater_app_final/dater_app_client/src/assets/chatHeart.png")))));
                        chatUserList.getItems().add(lbl);
                        m.setMatchListed(MATCH_LISTED);
                    }
                }
                // set neki label + dodaj u listu username

            }
            check = false;
        }

    }

    public void checkMessagesUpdateBadges(Boolean check){
        while(check){
            for(GeneralDomain msg : messages){
                Message m = (Message) msg;
                if(m.getMessageStatus().equals(UNREAD)){
                    // TODO update label
                }
            }
            check=false;
        }

    }

    private void setProfilePic(ImageView view) throws MalformedURLException {

        if (view.getId().equals(profilePic.getId())) {
            String photoUrl = currentUserPhotos.get(0).toURI().toURL().toString();
            Image image = new Image(photoUrl, 360, 360, true, true);
            profilePic.setImage(image);
            txtBiography1.setText(currentUser.getBio());
            lvlNameAge1.setText(currentUser.getFirstName() + " " + currentUser.getAge().getAge());
        }


        if (view.getId().equals(imgChangeProfile.getId())) {
            String photoUrl = currentUserPhotos.get(0).toURI().toURL().toString();
            Image image = new Image(photoUrl, 360, 360, true, true);
            imgChangeProfile.setImage(image);
        }

        if (view.getId().equals(imgMatchProfile.getId())) {
            String other = otherUserPhotos.get(iterator).toURI().toURL().toString();
            Image otherImage = new Image(other, 360, 360, true, true);
            imgMatchProfile.setImage(otherImage);
            profilePic.setImage(otherImage);
            txtBiography1.setText(matchUser.getBio());
            lvlNameAge1.setText(matchUser.getFirstName() + " " + matchUser.getAge().getAge());

        }
    }

    private void cycleImage(ImageView view) throws MalformedURLException {
        String photoUrl;
        Image image = null;
        if (incrementSelection == currentUserPhotos.size() - 1) {
            incrementSelection = -1;
        }
        incrementSelection++;
        if (!matchOrUser) {
            photoUrl = otherUserPhotos.get(incrementSelection).toURI().toURL().toString();
            image = new Image(photoUrl, 360, 360, true, true);
        } else if (matchOrUser) {
            photoUrl = currentUserPhotos.get(incrementSelection).toURI().toURL().toString();
            image = new Image(photoUrl, 360, 360, true, true);
        }


        if (view.getId().equals(imgChangeProfile.getId())) {
            imgChangeProfile.setImage(image);
        } else if (view.getId().equals(profilePic.getId())) {
            profilePic.setImage(image);
        } else if (view.getId().equals(imgMatchProfile.getId())) {
            imgMatchProfile.setImage(image);
        }

    }


    private void savePics(User u) throws IOException {
        List<byte[]> bytes = u.getP().getPictures();
        int length = bytes.size();
        File theDir = new File(WINDIRPICS + u.getUsername() + "/folder");
        Path newDir = Paths.get(theDir.getAbsolutePath());
        Files.createDirectories(newDir);
        Path path;
        for (int i = 0; i < length; i++) {
            byte[] b = bytes.get(i);
            path = Paths.get(newDir + u.getUsername() + i + ".jpg");
            try {
                write(path, b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadPics(User u) {
        File directory = null;
        boolean loggedUser = u.getUsername().equals(currentUser.getUsername());
        if (loggedUser) {
            directory = new File(WINDIRPICS + currentUser.getUsername());

        } else {
            directory = new File(WINDIRPICS + u.getUsername());
        }
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if ((!file.isDirectory()) && (file.getAbsolutePath().endsWith(".jpg")) && loggedUser) {
                currentUserPhotos.add(file);
            }
            if ((!file.isDirectory()) && (file.getAbsolutePath().endsWith(".jpg")) && !loggedUser) {
                otherUserPhotos.add(file);
            }
        }
    }

    private void MapViewClose() {
        this.mapView.close();
    }


    //Centers MapView
    private void MapView() {
        this.mapView.setBingMapsApiKey("AukPAgV0d7E9fohYeGVInZWVcxbXJbBrXRBDSD9iV7kBUF2VsxPtpO6BH9tv-PMe");
        this.mapView.setMapType(MapType.BINGMAPS_ROAD);
        this.mapView.setCenter(belgrade);
        this.mapView.initialize();
    }

    //Sets defined panels in the main scene
    private void setPaneIn(AnchorPane pane) {
        if (pane.getId().equals(drawerPane.getId())) {
            opacityPane.setVisible(true);
            FadeTransition opFade = new FadeTransition(Duration.seconds(0.5), opacityPane);
            opFade.setFromValue(0);
            opFade.setToValue(0.15);
            opFade.play();
        }
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), pane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), pane);
        translateTransition.setByX(322);
        translateTransition.play();
    }

    // Sets defined panels outside of the scene
    private void setPaneOut(AnchorPane pane, AnchorPane pane2) {
        if (pane.getId().equals(opacityPane.getId())) {
            opacityPane.setVisible(false);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.1), pane);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
            if (pane2.getId().equals(drawerPane.getId())) {
                fadeTransition.setOnFinished(Event1 -> {
                    opacityPane.setVisible(false);
                });
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), pane2);
                translateTransition.setByX(-322);
                translateTransition.play();
            }
        } else {
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.1), pane);
            transition.setByX(-322);
            transition.play();
        }

    }

    public Map<String, Object> getTestPicsforUser() {
        return testPicsforUser;
    }

    public void setTestPicsforUser(Map<String, Object> testPicsforUser) {

        this.testPicsforUser = testPicsforUser;
    }


    public static List<GeneralDomain> getMatches() {
        return matches;
    }

    public void setMatches(List<GeneralDomain> matches) {
        MainController.matches = matches;
    }

    public static List<GeneralDomain> getMessages() {
        return messages;
    }

    public void setMessages(List<GeneralDomain> messages) {
        this.messages = messages;
    }


    public void appendMessage(Message m) {
        if(m.getUserOneId().equals(item)){
            int hour = m.getMessagetime().toLocalDateTime().getHour();
            int minute = m.getMessagetime().toLocalDateTime().getMinute();
            this.chatWindowText.appendText("\n" +hour + ":"+ minute +"\n" + m.getUserOneId() + " : " +  m.getMessageBody());
        }

    }
}
