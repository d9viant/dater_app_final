package com.comtrade.controllerUI;

import com.comtrade.communication.Comm;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.LoginController;
import com.comtrade.view.MainController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

import static com.comtrade.domain.Constants.*;

public class Controller {
    private static Controller instance;
    private LoginController log;
    private Controller() {
        log=new LoginController();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void sendToServer(TransferClass tc) {
        Comm.getInstance().send(tc);
    }

    public void getFromServer(TransferClass tc) throws IOException {
        switch (tc.getOperation()) {
	        case USERNAME_TAKEN:
		        log.setCheckUser(java.lang.Boolean.TRUE);
            case WRONG_LOGIN:
                try{
                    Platform.runLater(() -> {
                        log.wrongCredentials();
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }

            case LOGIN:
                User u = (User) tc.getServer_object();
                try{
                    Platform.runLater(() -> {
                        try {
                            LoginController.changeWindow(u);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }


        }


    }


}
