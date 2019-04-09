package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class Pictures implements GeneralDomain, Serializable {
    private byte[] picture1;
    private byte[] picture2;
    private byte[] picture3;
    private byte[] picture4;
    private String pictureString1;
    private String pictureString2;
    private String pictureString3;
    private String pictureString4;
    private int readyForSql = 0;


    @Override
    public GeneralDomain fixSelect(ResultSet rs) throws SQLException {
        return null;
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
    public String returnInsertFormat() {
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


    public byte[] getPicture1() {
        return picture1;
    }

    public void setPicture1(byte[] picture1) {
        this.picture1 = picture1;
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

    public String getPictureString1() {
        return pictureString1;
    }

    public void setPictureString1(String pictureString1) {
        this.pictureString1 = pictureString1;
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
}
