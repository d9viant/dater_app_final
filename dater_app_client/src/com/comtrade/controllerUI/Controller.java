package com.comtrade.controllerUI;

import com.comtrade.communication.Comm;
import com.comtrade.domain.Constants;
import com.comtrade.transfer.TransferClass;

import java.io.IOException;

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

    public void serverRequestProcess(TransferClass tc) throws IOException, ClassNotFoundException {
        TransferClass tf = new TransferClass();
        tf.setOperation(Constants.CHECK_USER);
        tf.setOperation(Constants.SAVE_USER);
        tf.setClient_object(tc);
        Comm.getInstance().send(tf);




    }
}
