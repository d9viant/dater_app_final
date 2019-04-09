package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class Gender implements GeneralDomain, Serializable {
    private int userId;
    private char gender;
    private char preferredGender;
    private int readyForSql = 0;




    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public char getPreferredGender() {
        return preferredGender;
    }

    public void setPreferredGender(char preferredGender) {
        this.preferredGender = preferredGender;
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
    public String returnUserName(GeneralDomain gd) {
        return null;
    }

    public int getReadyForSql() {
        return readyForSql;
    }

    public void setReadyForSql(int readyForSql) {
        this.readyForSql = readyForSql;
    }
}
