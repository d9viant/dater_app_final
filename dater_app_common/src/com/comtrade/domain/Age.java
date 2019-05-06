package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;

import static com.comtrade.domain.Constants.DBWRITTEN;
import static com.comtrade.domain.Constants.RDYFORDB;

public class Age implements GeneralDomain, Serializable {
    private String username;
    private int age;
    private int readyForSql = DBWRITTEN;

    public Age(){

    }

    public int getAgeInYears() {
        return age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        return "age";
    }

    @Override
    public String returnTableRows() {
        return " (username, age) ";
    }

    @Override
    public String returnInsertFormat() {
        return "VALUES ('" + username + "','" + age + "')";

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
