package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;

public class BatchThread extends Thread{
    public void run() {
        while(true){
            try {
                sleep(600000);
                ControllerBLogic.getInstance().saveBatch(ControllerBLogic.getInstance().getDatathread().getGetAllUserList());
                ControllerBLogic.getInstance().saveMatchBatch(ControllerBLogic.getInstance().getDatathread().getAllMatches());
                ControllerBLogic.getInstance().saveMessageBatch(ControllerBLogic.getInstance().getDatathread().getAllMessages());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
