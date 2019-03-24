package com.comtrade.thread;

import com.comtrade.communication.Comm;
import com.comtrade.controllerUI.Controller;
import com.comtrade.transfer.TransferClass;

public class ProcessingFromServer extends Thread {
    public void run() {
        while (true) {
            TransferClass tc;
            try {
                tc = Comm.getInstance().read();
                Controller.getInstance().getFromServer(tc);

            } catch (Exception e) {
//				e.printStackTrace();
                break;
            }
        }
    }
}
