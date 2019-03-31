package com.comtrade.view;

import com.comtrade.controllerUI.Controller;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.geoloc.GeoLoc;
import com.comtrade.transfer.TransferClass;
import com.jfoenix.controls.*;
import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapViewEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static com.comtrade.domain.Constants.CHECK_USER;
import static com.comtrade.domain.Constants.SAVE_USER;
//import net.coobird.thumbnailator.Thumbnails;
//import net.coobird.thumbnailator.name.Rename;

public class LoginController implements Initializable, Serializable {

    @FXML
    private MapView mapViewLogin;

    @FXML
    private AnchorPane mapPaneLogin;

    @FXML
    private Pane regPane, loginpane;

    @FXML
    private JFXTextField btnName, btnLastName, txtUsername, btnLoc, txtLoc, txtLoginUsername;

    @FXML
    private JFXRadioButton radioM, radioF;

    @FXML
    private JFXPasswordField txtPassword, txtPasswordRepeat;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXPasswordField passField, txtLoginPassword;

    @FXML
    private JFXButton btnLogin, btnReg, btnBack, btnSignUp, btnLocation, btnMapCoord, btnUplProf;

    @FXML
    private MediaView logMedia;

    @FXML
    private ImageView uplProfile;

    private MapLabel labelClick;

    private final ToggleGroup tGroup = new ToggleGroup();

    private String[] redStrings;

    final FileChooser fileChooser = new FileChooser();

    private File selectedFile;

    private Path to;

    private Path from;

    private Coordinate belgrade = new Coordinate(44.7866, 20.4489);

    private Coordinate click;

    private Marker markerClick = Marker.createProvided(Marker.Provided.ORANGE).setVisible(false);

    private static ArrayList<Double> red = new ArrayList<>(2);

    private GeoLoc g=null;

    private Boolean checkUser = null;

    public void setCheckUser(Boolean checkUser) {
        this.checkUser = checkUser;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        radioM.setToggleGroup(tGroup);
        radioM.setSelected(true);
        radioF.setToggleGroup(tGroup);
        mapPaneLogin.setVisible(false);
        loadVideo();
        try {
            startGeoLoc();
        } catch (IOException e) {
            e.printStackTrace();
        }


        btnMapCoord.setOnAction(this::showMapCoord);

        btnUplProf.setOnAction(this::uploadPhoto);

        btnSignUp.setOnAction(this::createUser);

        btnLocation.setOnMouseClicked(Event -> {
            try {
               red = g.location();
            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            redStrings = g.ReverseGeoLoc();
            txtLoc.setText(redStrings[1] + " " + redStrings[0]);
        });

        btnLogin.setOnAction(Event -> {
            String loginString = txtLoginUsername.getText();
            String passString = txtLoginPassword.getText();
            User u = new User();
            u.setUsername(loginString);
            u.setPass(passString);
//			login(u, Event);
        });
        btnReg.setOnAction(Event -> {
            regPane.setVisible(true);
            loginpane.setVisible(false);
        });
        btnBack.setOnAction(Event -> {
            regPane.setVisible(false);
            loginpane.setVisible(true);
        });
    }

    private void startGeoLoc() throws IOException {
        g = new GeoLoc();
    }

    private void MapView() {
        this.mapViewLogin.setBingMapsApiKey("AukPAgV0d7E9fohYeGVInZWVcxbXJbBrXRBDSD9iV7kBUF2VsxPtpO6BH9tv-PMe");
        this.mapViewLogin.setMapType(MapType.BINGMAPS_ROAD);
        this.mapViewLogin.setCenter(belgrade);
        this.mapViewLogin.initialize();
    }

    private void createUser(ActionEvent event) {
        User newUser = new User();
        TransferClass tf = new TransferClass();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("You are about to create your profile, are you sure that you want to continue?");
        alert.setTitle(null);
        alert.setContentText(null);
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {
                if ((btnName.getText().equals("")) ||

                        (btnLastName.getText().equals("")) ||

                        (txtUsername.getText().equals("")) ||

                        (txtPassword.getText().equals("")) ||

                        (datePicker.getValue().toString().equals("")) ||

                        (txtLoc.getText().equals(""))) {

                    Alert inputAlert = new Alert(AlertType.WARNING);
                    inputAlert.setHeaderText(null);
                    inputAlert.setTitle("Error");
                    inputAlert.setContentText("Please fill all input fields");
                    inputAlert.showAndWait();

                } else {
                    String firstNameString = btnName.getText();
                    newUser.setFirstName(firstNameString);
                    String lastNameString = btnLastName.getText();
                    newUser.setLastName(lastNameString);
                    String usernameString = txtUsername.getText();
                    newUser.setUsername(usernameString);

                    if (radioM.isSelected()) {
                        newUser.getGender().setGender('m');
                    } else {
                        newUser.getGender().setGender('z');
                    }

                    String birthdateString = datePicker.getValue().toString();
                    LocalDate date = LocalDate.parse(birthdateString);
                    newUser.getAge().setBirthday(date);
                    String locationString = txtLoc.getText();
                    System.out.println(locationString);
                    newUser.getLocation().setAddress(locationString);

                    try {
                        newUser.getLocation().setLatitude(red.get(0).doubleValue());
                        newUser.getLocation().setLongitude(red.get(1).doubleValue());
                    } catch (Exception e) {
                        System.out.println("Nema Koordinata");
                    }
                    // Check password fields
                    if (!txtPassword.getText().equals(txtPasswordRepeat.getText())) {
                        Alert passAlert = new Alert(AlertType.WARNING);
                        passAlert.setHeaderText(null);
                        passAlert.setTitle("Error");
                        passAlert.setContentText("Passwords Are Not Matching");
                        passAlert.showAndWait();

                    } else {
                        String passwrString = txtPassword.getText();
                        newUser.setPass(passwrString);

                        // Check if profile picture is selected
                        if (selectedFile == null) {
                            Alert pAlert = new Alert(AlertType.WARNING);
                            pAlert.setHeaderText(null);
                            pAlert.setTitle("Error");
                            pAlert.setContentText("Please upload profile picture");
                            pAlert.showAndWait();
                        } else {

                            String userPhotoString = newUser.getUsername() + selectedFile.getName();
                            newUser.setUserPhoto(userPhotoString);

                            // Check if Username is in DB
                            Map<String, GeneralDomain> checkMap = new HashMap<>();
                            System.out.println(newUser.getFirstName());
                            tf.setClient_object(newUser);
	                        tf.setOperation(CHECK_USER);
	                        Controller.getInstance().sendToServer(tf);

                            if (checkUser) {
                                Alert bye = new Alert(AlertType.WARNING);
                                bye.setHeaderText(null);
                                bye.setTitle("Error");
                                bye.setContentText("Username is taken, please change your username");
                                bye.showAndWait();
                            } else {
                                // Copy picture file to folder on server
                                from = Paths.get(selectedFile.toURI());
                                to = Paths.get("src\\ProfilePics\\" + newUser.getUsername() + selectedFile.getName());
                                try {
                                    Files.copy(from, to);
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                tf.setClient_object(newUser);
                                System.out.println(newUser.getFirstName());

	                            tf.setOperation(SAVE_USER);
	                            Controller.getInstance().sendToServer(tf);
                            }
                        }
                    }
                }
            }
        });
    }

    private void loadVideo() {
        regPane.setVisible(false);

        String vurl = "/home/strahinja/IdeaProjects/dater_app_final/dater_app_client/src/assets/love.mp4";
        Media media = new Media(new File(vurl).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        logMedia.setMediaPlayer(player);
        player.setVolume(0);
	    player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
    }

//	private void login(User u, ActionEvent Event){
//		IProxy p = new ProxyLogin(u, Event);
//		p.login(u);
//	}
//	private boolean checkUser(User u, ActionEvent Event){
//		IProxy p = new ProxyLogin(u, Event);
//		return  p.check(u);
//	}

    private void uploadPhoto(ActionEvent event) {
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("PICTURE files (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String photoUrl = null;
            try {
                photoUrl = selectedFile.toURI().toURL().toString();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            Image image = new Image(photoUrl, 360, 360, false, true);
//			try {
////				Thumbnails.of(selectedFile)
////						.size(160, 160)
////						.outputFormat("jpg")
////						.toFiles(Rename.PREFIX_DOT_THUMBNAIL);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

            uplProfile.setImage(image);
        }
    }

    private void showMapCoord(ActionEvent Event) {
        MapView();
        mapPaneLogin.setVisible(true);
        mapPaneLogin.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
            event.consume();
            System.out.println("Event: map clicked at: " + event.getCoordinate());
            mapViewLogin.addMarker(markerClick);
            markerClick.setPosition(event.getCoordinate()).setVisible(true);
            this.click = event.getCoordinate();
            double latitude = click.getLatitude();
            double longitude = click.getLongitude();
            red.add(0, latitude);
            red.add(1, longitude);
            g.setRed(red);
            redStrings = g.ReverseGeoLoc();
            txtLoc.setText(redStrings[1] + " " + redStrings[0]);
        });
    }
}
