package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;

import javax.swing.*;

public class BatchThread extends Thread{


    private static volatile boolean killthread=false;
    private JTextArea txtServerLogs;
    private JProgressBar progressBar1;
    public BatchThread(JTextArea textStart, JProgressBar progressBar) {
        this.txtServerLogs=textStart;
        this.progressBar1=progressBar;
    }

    public void run() {
        System.out.println("Started batch");
        while(!killthread){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(killthread){
                System.out.println("its true");
                try {
                    txtServerLogs.append("\n"+"Backup thread on standby");
                    Thread.sleep(60000);
                    ControllerBLogic.getInstance().getDatathread().saveBatch(txtServerLogs, progressBar1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }





    }
    public static void setKillthread(boolean killthread) {
        System.out.println("set to true");
        BatchThread.killthread = killthread;
    }
}
