package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;

public class BatchThread extends Thread{
    public void run() {
        while(true){
            try {
                Thread.sleep(5000);
                System.out.println("kurcina"+ControllerBLogic.getInstance().getDatathread().getGetAllUserList().size());


//                ControllerBLogic.getInstance().saveBatch(ControllerBLogic.getInstance().getDatathread().getGetAllUserList());
//                ControllerBLogic.getInstance().saveMatchBatch(ControllerBLogic.getInstance().getDatathread().getAllMatches());
//                ControllerBLogic.getInstance().saveMessageBatch(ControllerBLogic.getInstance().getDatathread().getAllMessages());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
