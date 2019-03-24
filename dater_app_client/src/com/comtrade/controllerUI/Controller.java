package com.comtrade.controllerUI;

import com.comtrade.communication.Comm;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.LoginController;

import static com.comtrade.domain.Constants.USERNAME_TAKEN;

public class Controller {
    private static Controller instance;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void sendToServer(TransferClass tc) {
//        TransferClass tf = new TransferClass();
//        tf.setClient_object(tc);
        Comm.getInstance().send(tc);
    }

    public void getFromServer(TransferClass tc) {
        switch (tc.getOperation()) {
	        case USERNAME_TAKEN:
		        LoginController log = new LoginController();
		        log.setCheckUser(java.lang.Boolean.TRUE);

        }


    }
}
