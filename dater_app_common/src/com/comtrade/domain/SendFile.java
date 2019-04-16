package com.comtrade.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SendFile implements Serializable {
    private byte[] files;
    private int idSender;
    private List<Integer> listOfRecievers =new ArrayList<>();


    public List<Integer> getListOfRecievers() {
        return listOfRecievers;
    }

    public void setListOfRecievers(ArrayList<Integer> listOfRecievers) {
        this.listOfRecievers = listOfRecievers;
    }

    public SendFile(byte[] files) {

        this.files = files;
    }

    public byte[] getFiles() {
        return files;
    }

    public void setFiles(byte[] files) {
        this.files = files;
    }



    public void setListOfRecievers(List<Integer> listOfRecievers) {
        this.listOfRecievers = listOfRecievers;
    }

}
