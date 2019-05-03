package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static com.comtrade.domain.Constants.RDYFORDB;

public class Gender implements GeneralDomain, Serializable {
    private String username;
    private int gender;
    private int preferredGender;
    private int readyForSql = RDYFORDB;

    public Gender() {

    }

    public Gender(String username, int gender, int preferredGender) {
        this.username = username;
        this.gender = gender;
        this.preferredGender = preferredGender;

    }





    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getPreferredGender() {
        return preferredGender;
    }

    public void setPreferredGender(int preferredGender) {
        this.preferredGender = preferredGender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
}
