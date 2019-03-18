package com.comtrade.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SendFile implements Serializable {

    private byte[] picture;
    private String fileName;
    private int idSender;
    private List<Integer> listOfRecievers =new ArrayList<>();

    public List<Integer> getListOfRecievers() {
        return listOfRecievers;
    }

    public void setListOfRecievers(ArrayList<Integer> listOfRecievers) {
        this.listOfRecievers = listOfRecievers;
    }

    public SendFile(byte[] picture) {
        super();
        this.picture = picture;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }
}
