package com.comtrade.controllerUI;

import com.comtrade.communication.Comm;
import com.comtrade.transfer.TransferClass;

import static com.comtrade.domain.Constants.RETURN_PROFILE;

public class Controller {
    private static Controller instance;
    private Object Boolean;


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
            case RETURN_PROFILE:
        }


    }
}
