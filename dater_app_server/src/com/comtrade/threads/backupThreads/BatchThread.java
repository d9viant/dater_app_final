package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;

import javax.swing.*;

public class BatchThread extends Thread{
    private JTextArea txtServerLogs;
    private JProgressBar progressBar1;
    public BatchThread(JTextArea textStart, JProgressBar progressBar) {
        this.txtServerLogs=textStart;
        this.progressBar1=progressBar;
    }

    public void run() {
            try {
                txtServerLogs.append("Backup thread is locked and loaded");
                ControllerBLogic.getInstance().getDatathread().saveBatch(txtServerLogs, progressBar1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




    }
}
