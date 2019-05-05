package com.comtrade.controllerUI;

import com.comtrade.communication.Comm;
import com.comtrade.domain.User;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.LoginController;

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
                User login =
        }


    }
}
