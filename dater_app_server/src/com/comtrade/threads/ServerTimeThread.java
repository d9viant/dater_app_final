package com.comtrade.threads;

import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerTimeThread extends Thread{
    private TextField timeText;

    public ServerTimeThread(TextField timeText) {
        this.timeText=timeText;
    }

    public void run(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        while(true){
            timeText.setText(sdf.format(new Date()));
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
