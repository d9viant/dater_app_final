package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static com.comtrade.domain.Constants.DBWRITTEN;
import static com.comtrade.domain.Constants.RDYFORDB;

public class Matches implements Serializable, GeneralDomain{
    private String usernameOne;
    private String usernameTwo;
    private String requestUsername;
    private boolean matchStatus;
    private int readyForSql = DBWRITTEN;

    public Matches(){

    }

    public Matches(String userOneId, String userTwoId, String requestUser, boolean actionStatus) {
        this.usernameOne = userOneId;
        this.usernameTwo = userTwoId;
        this.requestUsername = requestUser;
        this.matchStatus = actionStatus;
    }



    public boolean isMatchStatus() {
        return matchStatus;
    }

    public String getUsernameOne() {
        return usernameOne;
    }

    public void setUsernameOne(String usernameOne) {
        this.usernameOne = usernameOne;
    }

    public String getUsernameTwo() {
        return usernameTwo;
    }

    public void setUsernameTwo(String usernameTwo) {
        this.usernameTwo = usernameTwo;
    }

    public String getRequestUsername() {
        return requestUsername;
    }

    public void setRequestUsername(String requestUsername) {
        this.requestUsername = requestUsername;
    }

    public void setMatchStatus(boolean matchStatus) {
        this.matchStatus = matchStatus;
    }
    @Override


    public GeneralDomain fixSelect(ResultSet rs) throws SQLException {
        Matches m = null;
        while (rs.next()) {
            String userOneId = rs.getString("usernameOne");
            String userTwoId = rs.getString("usernameTwo");
            String requestUser = rs.getString("requestUsername");
            boolean matchStatus = rs.getBoolean("matchStatus");
            m = new Matches(userOneId, userTwoId, requestUser, matchStatus);

        }
        return m;
    }

    @Override
    public String returnInnerJoin() {
        return null;
    }

    @Override
    public String returnTableName() {
        return "matchtable";
    }

    @Override
    public String returnTableRows() {
        return " (usernameOne, usernameTwo, requestUsername, matchStatus) ";
    }

    @Override
    public String returnInsertFormat() {
        return "VALUES ('" + usernameOne + "','" + usernameTwo + "','" + requestUsername + "','" + matchStatus + "')";
    }

    @Override
    public String delete(GeneralDomain gd) {
        Matches m = (Matches) gd;
        return " WHERE userOneId="+m.getUsernameOne()+" AND userTwoId="+m.getUsernameTwo()+"";

    }

    @Override
    public HashMap<String, GeneralDomain> fixInnerSelect(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public HashMap<String, List<GeneralDomain>> fixInnerSelectList(ResultSet rs) throws SQLException {
        HashMap<String, List<GeneralDomain>> matchesHM = new HashMap<>();
        while(rs.next()){

        }



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
