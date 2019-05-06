package com.comtrade.view;

import com.comtrade.compression.Compression;
import com.comtrade.controllerUI.Controller;
import com.comtrade.domain.Pictures;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRadioButton;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapType;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import static com.comtrade.domain.Constants.LIKE;
import static com.comtrade.domain.Constants.WINDIRPICS;
import static java.nio.file.Files.write;

public class MainController implements Initializable, Serializable {
    @FXML
    private AnchorPane mapPane;

    @FXML
    private MapView mapView;

    @FXML
    private AnchorPane rootPane, settingsPane, chatPane;

    @FXML
    private ImageView imgProfile, imgChangeProfile;

    @FXML
    private Label lvlNameAge;

    @FXML
    private TextArea bioTextFieldOpacity;

    @FXML
    private Label txtBiography;

    @FXML
    private JFXButton btnDislike, btnBoost, btnHeart, btnSuper, btnRevert, btnProfileToMain, btnStalkerGlobe, drawerProfile, drawerChat, drawerSettings, btnStalkerToMain, btnNadji, settingsBackToMain, btnChangeProf, backFromChatToMain;

    @FXML
    private JFXHamburger drawerImg;

    @FXML
    private AnchorPane profilePane;

    @FXML
    private Label lvlNameAge1;

    @FXML
    private Label txtBiography1;

    @FXML
    private ImageView imgStalkerGlobe, profilePic;

    @FXML
    private AnchorPane opacityPane;

    @FXML
    private AnchorPane placeHolderPane;

    @FXML
    private AnchorPane drawerPane;

    @FXML
    private JFXPopup popup = new JFXPopup();

    @FXML
    private JFXRadioButton menCheckbox, womanCheckbox;

    private Coordinate belgrade = new Coordinate(44.7866, 20.4489);

    private Coordinate matchCoord = new Coordinate(44.819977, 20.5080518);

    private final ToggleGroup tGroup = new ToggleGroup();

    private User currentUser;

    List<File> list;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private Marker matchMarker = Marker.createProvided(Marker.Provided.BLUE).setPosition(matchCoord).setVisible(true);

    final FileChooser fileChooser = new FileChooser();

    int incrementSelection = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialSetup();
        controlButtons();

    }


    private void initialSetup() {
//        setPaneOut(settingsPane, placeHolderPane);
        setPaneOut(chatPane, placeHolderPane);
        setPaneOut(opacityPane, placeHolderPane);
        setPaneOut(drawerPane, placeHolderPane);
        setPaneOut(profilePane, placeHolderPane);
        setPaneOut(mapPane, placeHolderPane);
        imgStalkerGlobe.setVisible(false);
        btnStalkerGlobe.setVisible(false);
        menCheckbox.setToggleGroup(tGroup);
        womanCheckbox.setToggleGroup(tGroup);
        womanCheckbox.setSelectedColor(Color.web("ff6969"));
        menCheckbox.setSelectedColor(Color.web("ff6969"));

    }
    private void controlButtons() {


        imgChangeProfile.setOnMouseClicked(Event ->{
            if(incrementSelection == list.size()-1){
                incrementSelection = -1;
            }
            incrementSelection++;
            System.out.println(incrementSelection);
            try {
                String photoUrl = list.get(incrementSelection).toURI().toURL().toString();
                Image image = new Image(photoUrl, 360, 360, true, true);
                imgChangeProfile.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });


        drawerChat.setOnAction(Event -> {
            setPaneIn(chatPane);
            setPaneOut(opacityPane, drawerPane);
        });


        backFromChatToMain.setOnAction(Event -> {
            setPaneOut(chatPane, placeHolderPane);
        });

        settingsBackToMain.setOnAction(Event -> setPaneOut(settingsPane, placeHolderPane));

        opacityPane.setOnMouseClicked(Event -> setPaneOut(opacityPane, drawerPane));

        btnProfileToMain.setOnAction(Event -> setPaneOut(profilePane, placeHolderPane));

        drawerImg.setOnMouseClicked(Event -> setPaneIn(drawerPane));

        drawerSettings.setOnAction(Event -> {
            setPaneIn(settingsPane);
            setPaneOut(opacityPane, drawerPane);
        });

        btnNadji.setOnAction(Event -> {
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

        drawerProfile.setOnAction(Event -> {
            setProfilePic();
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
            list = fileChooser.showOpenMultipleDialog(null);
            if (list!=null) {
                String photoUrl = null;
                for(int i=0; i<list.size();i++){
                    File fi = list.get(i);
                    try {
                        photoUrl = list.get(0).toURI().toURL().toString();
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

            tf.setOperation(LIKE);
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


    private static void savePics(User u) throws IOException {
        List<byte[]> bytes = u.getP().getPictures();
        int length = bytes.size();
        File theDir = new File(WINDIRPICS);
        Path newDir = Paths.get(theDir.getAbsolutePath());
        Files.createDirectories(newDir);
        for(int i=0; i<length; i++){
            byte[] b = bytes.get(i);
            Path path = Paths.get(  newDir + u.getUsername() + i + ".jpg");
            try {
                write(path, b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void setProfilePic() {
//        String profile = "file:src\\ProfilePics\\" + currentUser.getUserPhoto();
//        Image profileImage = new Image(profile, 200, 200, false, false);
//        profilePic.setImage(profileImage);
//        System.out.println(currentUser.getAge().getAgeInYears());
//        lvlNameAge1.setText(String.valueOf(currentUser.getAge().getAgeInYears()));
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


}
