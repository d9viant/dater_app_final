package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pictures implements GeneralDomain, Serializable {
    private byte[] picture;
    private List<byte[]> pictures = new ArrayList<byte[]>();
    private String pictureString;
    private String pictureString2;
    private String pictureString3;
    private String pictureString4;
    private String pictureString5;
    private int readyForSql = 0;


    @Override
    public GeneralDomain fixSelect(ResultSet rs) throws SQLException {
        return null;
    }


    public Pictures(String pictureString, String pictureString2, String pictureString3, String pictureString4, int readyForSql) {

        this.pictureString = pictureString;
        this.pictureString2 = pictureString2;
        this.pictureString3 = pictureString3;
        this.pictureString4 = pictureString4;
        this.readyForSql = readyForSql;
    }

    public Pictures() {

    }

    @Override
    public String returnInnerJoin() {
        return null;
    }

    @Override
    public String returnTableName() {
        return null;
    }

    @Override
    public String returnTableRows() {
        return null;
    }

    @Override
    public String returnInsertFormat(GeneralDomain gd) {
        return null;
    }

    @Override
    public String delete(GeneralDomain gd) {
        return null;
    }

    @Override
    public HashMap<String, GeneralDomain> fixInnerSelect(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public HashMap<String, List<GeneralDomain>> fixInnerSelectList(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public String returnUserName(GeneralDomain gd) {
        return null;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPictureString() {
        return pictureString;
    }

    public void setPictureString(String pictureString) {
        this.pictureString = pictureString;
    }


    public void setProfilePicture(String profilePictureString) {
        this.pictureString = profilePictureString;
    }

    public String getprofilePictureString() {
        return pictureString;
    }

    public String getPictureString2() {
        return pictureString2;
    }

    public void setPictureString2(String pictureString2) {
        this.pictureString2 = pictureString2;
    }

    public String getPictureString3() {
        return pictureString3;
    }

    public void setPictureString3(String pictureString3) {
        this.pictureString3 = pictureString3;
    }

    public String getPictureString4() {
        return pictureString4;
    }

    public void setPictureString4(String pictureString4) {
        this.pictureString4 = pictureString4;
    }

    public int getReadyForSql() {
        return readyForSql;
    }

    public void setReadyForSql(int readyForSql) {
        this.readyForSql = readyForSql;
    }

    public List<byte[]> getPictures() {
        return pictures;
    }

    public void setPictures(List<byte[]> pictures) {
        this.pictures = pictures;
    }
    public String getPictureString5() {
        return pictureString5;
    }

    public void setPictureString5(String pictureString5) {
        this.pictureString5 = pictureString5;
    }
}
