package com.comtrade.domain;

import java.io.File;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pictures implements GeneralDomain, Serializable {
    private List<byte[]> pictures = new ArrayList<>();
    List<File> pictureFiles = new ArrayList<>();

    private int readyForSql = 0;


    @Override
    public GeneralDomain fixSelect(ResultSet rs) throws SQLException {
        return null;
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
    public String returnUserName() {
        return null;
    }

    @Override
    public String getForSelectForSpecific(GeneralDomain u) {
        return null;
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

    public List<File> getPictureFiles() {
        return pictureFiles;
    }

    public void setPictureFiles(List<File> pictureFiles) {
        this.pictureFiles = pictureFiles;
    }


}
