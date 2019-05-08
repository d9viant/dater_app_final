package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;

import javax.swing.*;

public class DataStorageThread extends Thread{
    private JTextArea txtServerLogs;
    JTextArea backupLogs;
    JProgressBar progressBar1;
    private static volatile boolean killthread = false;
    private static volatile boolean batchstart = false;


    public DataStorageThread(JTextArea textStart, JTextArea backupLogs, JProgressBar progressBar1) {
        this.txtServerLogs=textStart;
        this.backupLogs = backupLogs;
        this.progressBar1 = progressBar1;


    }

    public void run(){

        try {
            Thread.sleep(3000);
            while(!killthread){
                    ControllerBLogic.getInstance().getDatathread().getData(backupLogs, progressBar1);
                    txtServerLogs.append("\n" + "Data Loaded Into Memory");
                    killthread=true;
            }
            System.out.println("data thread killed");
            BatchThread.setKillthread(true);



        } catch (InterruptedException e) {
            System.out.println("DataStorageThread has Failed to start");
        }
    }

    public static void setBatchstart(boolean batchstart) {
        DataStorageThread.batchstart = batchstart;
    }
}
