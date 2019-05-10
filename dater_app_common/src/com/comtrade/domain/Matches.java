package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.comtrade.domain.Constants.*;

public class Matches implements Serializable, GeneralDomain{
    private String usernameOne;
    private String usernameTwo;
    private String requestUsername;
    private int matchStatus = 0;
    private int readyForSql = DBWRITTEN;
    private int matchListed = MATCH_UNLISTED;

    public Matches(){

    }

    public Matches(String userOneId, String userTwoId, String requestUser, int matchStatus) {
        this.usernameOne = userOneId;
        this.usernameTwo = userTwoId;
        this.requestUsername = requestUser;
        this.matchStatus = matchStatus;
    }



    public int isMatchStatus() {
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

    public void setMatchStatus(int matchStatus) {
        this.matchStatus = matchStatus;
    }

    public int getMatchStatus() {
        return matchStatus;
    }
    @Override


    public GeneralDomain fixSelect(ResultSet rs) throws SQLException {
        Matches m = null;
        while (rs.next()) {



        }
        return m;
    }

    @Override
    public String returnInnerJoin() {
        return "SELECT * FROM user INNER JOIN matchtable ON (user.username=matchtable.usernameOne OR user.username=matchtable.usernameTwo)";
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
        return "VALUES ('" + usernameOne + "','" + usernameTwo + "','" + requestUsername + "','" + matchStatus + "') " + "ON DUPLICATE KEY UPDATE matchStatus='" + matchStatus+ "'";
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
            String username = rs.getString("username").toLowerCase();
            String userOneId = rs.getString("usernameOne");
            String userTwoId = rs.getString("usernameTwo");
            String requestId = rs.getString("requestUsername");
            int matchStatus = rs.getInt("matchStatus");
            Matches m = new Matches(userOneId, userTwoId,requestId,  matchStatus);
            if (matchesHM.containsKey(username)) {
                matchesHM.get(username).add(m);
            } else {
                List<GeneralDomain> messageList = new ArrayList<>();
                matchesHM.put(username, messageList);
                matchesHM.get(username).add(m);
            }
        }



        return matchesHM;
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


    public int getMatchListed() {
        return matchListed;
    }

    public void setMatchListed(int matchListed) {
        this.matchListed = matchListed;
    }
}
