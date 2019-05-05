package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;

import javax.swing.*;

public class DataStorageThread extends Thread{
    private JTextArea txtServerLogs;
    JTextArea backupLogs;
    JProgressBar progressBar1;

    public DataStorageThread(JTextArea textStart, JTextArea backupLogs, JProgressBar progressBar1) {
        this.txtServerLogs=textStart;
        this.backupLogs = backupLogs;
        this.progressBar1 = progressBar1;


    }

    public void run(){
        try {
            ControllerBLogic.getInstance().getDatathread().getData(backupLogs, progressBar1);
            txtServerLogs.append("\n" + "Data Loaded Into Memory");
        } catch (InterruptedException e) {
            System.out.println("DataStorageThread has Failed to start");
        }
    }


}
