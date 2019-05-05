package com.comtrade.controllerUI;

import com.comtrade.communication.Comm;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.LoginController;
import com.comtrade.view.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Main;

import static com.comtrade.domain.Constants.*;

public class Controller {
    private static Controller instance;
    private LoginController log = new LoginController();
    private Controller() {

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

    public void getFromServer(TransferClass tc) {
        switch (tc.getOperation()) {
	        case USERNAME_TAKEN:
		        log.setCheckUser(java.lang.Boolean.TRUE);
            case USERNAME_OK:
                log.setCheckUser(Boolean.FALSE);

            case LOGIN:

                Stage stage = Main.stage;
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/comtrade/viewLayout/mainscreen.fxml"));
                AnchorPane pane = loader.load();
                Scene scene = new Scene(pane);
                stage.setResizable(false);
                stage.setTitle("Dater App! Find true love!");
                stage.setScene(scene);

//      MainController controller = loader.<MainController>getController();
                MainController controller = loader.getController();
//      controller.setCurrentUser(currUser);   Sets current user!
                stage.show();
        }


    }
}
