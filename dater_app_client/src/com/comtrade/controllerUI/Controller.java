package com.comtrade.controllerUI;

import com.comtrade.communication.Comm;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.Matches;
import com.comtrade.domain.Rating;
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
import java.util.List;
import java.util.Map;

import static com.comtrade.domain.Constants.*;

public class Controller {
    private static Controller instance;
    private LoginController log;
    private MainController main;

    private Controller() {
        log = new LoginController();
        main = new MainController();
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
                try {
                    Platform.runLater(() -> {
                        log.wrongCredentials();
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case LOGIN:
                Map<String, Object> testPicsforUser = (Map<String, Object>) tc.getServer_object();
                try {
                    Platform.runLater(() -> {
                        try {
                            LoginController.changeWindow(testPicsforUser);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case UPDATE_RATING:
                Rating r = (Rating) tc.getServer_object();
                MainController.getCurrentUser().setR(r);
                break;
            case UPDATE_MATCH:
                Matches update = (Matches) tc.getServer_object();
                List<GeneralDomain> matchess = MainController.getMatches();
                for (GeneralDomain match : matchess) {
                    Matches m = (Matches) match;
                    if (m.getUsernameOne().equals(update.getRequestUsername()) || m.getUsernameTwo().equals(update.getRequestUsername())) {
                        m.setMatchStatus(MATCHED);
                    }
                }
                main.checkMatchesUpdateBadges(true);
                break;
            case CREATE_MATCH:
                Matches create = (Matches) tc.getServer_object();
                MainController.getMatches().add(create);
                break;
            case DELETE_MATCH:

                break;
        }


    }


}
