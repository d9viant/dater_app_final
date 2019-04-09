package com.comtrade.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SendFile implements Serializable {
    private byte[] picture;
    private byte[] picture2;
    private byte[] picture3;
    private byte[] picture4;
    private byte[] picture5;
    private String fileName;
    private String fileName2;
    private String fileName3;
    private String fileName4;
    private String fileName5;
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

    public byte[] getPicture2() {
        return picture2;
    }

    public void setPicture2(byte[] picture2) {
        this.picture2 = picture2;
    }

    public byte[] getPicture3() {
        return picture3;
    }

    public void setPicture3(byte[] picture3) {
        this.picture3 = picture3;
    }

    public byte[] getPicture4() {
        return picture4;
    }

    public void setPicture4(byte[] picture4) {
        this.picture4 = picture4;
    }

    public byte[] getPicture5() {
        return picture5;
    }

    public void setPicture5(byte[] picture5) {
        this.picture5 = picture5;
    }

    public String getFileName2() {
        return fileName2;
    }

    public void setFileName2(String fileName2) {
        this.fileName2 = fileName2;
    }

    public String getFileName3() {
        return fileName3;
    }

    public void setFileName3(String fileName3) {
        this.fileName3 = fileName3;
    }

    public String getFileName4() {
        return fileName4;
    }

    public void setFileName4(String fileName4) {
        this.fileName4 = fileName4;
    }

    public String getFileName5() {
        return fileName5;
    }

    public void setFileName5(String fileName5) {
        this.fileName5 = fileName5;
    }

    public void setListOfRecievers(List<Integer> listOfRecievers) {
        this.listOfRecievers = listOfRecievers;
    }

}
