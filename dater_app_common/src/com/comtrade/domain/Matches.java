package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Matches implements Serializable, GeneralDomain{
    private int userOneId;
    private int userTwoId;
    private int requestUser;
    private boolean matchStatus;

    public Matches(){

    }

    public Matches(int userOneId, int userTwoId, int requestUser, boolean actionStatus){
        this.userOneId=userOneId;
        this.userTwoId=userTwoId;
        this.requestUser = requestUser;
        this.matchStatus = actionStatus;
    }


    public int getUserOneId() {
        return userOneId;
    }

    public void setUserOneId(int userOneId) {
        this.userOneId = userOneId;
    }

    public int getUserTwoId() {
        return userTwoId;
    }

    public void setUserTwoId(int userTwoId) {
        this.userTwoId = userTwoId;
    }

    public int getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(int requestUser) {
        this.requestUser = requestUser;
    }

    public boolean isMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(boolean matchStatus) {
        this.matchStatus = matchStatus;
    }
    @Override
    public List<GeneralDomain> fixSelect(ResultSet rs) throws SQLException {
        List<GeneralDomain> list = new ArrayList<>();
        while (rs.next()) {
            int userOneId = rs.getInt("userOne");
            int userTwoId = rs.getInt("userTwo");
            int requestUser = rs.getInt("requestUser");
            boolean matchStatus = rs.getBoolean("matchStatus");
            Matches m = new Matches(userOneId, userTwoId, requestUser, matchStatus);
            list.add(m);
        }
        return list;
    }

    @Override
    public String returnTableName() {
        return "matchtable";
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
        Matches m = (Matches) gd;
        if (m.getUserOneId()<m.getUserTwoId()) {
            return " WHERE userOneId="+m.getUserOneId()+" AND userTwoId="+m.getUserTwoId()+"";
        }else {
            return " WHERE userOneId="+m.getUserTwoId()+" AND userTwoId="+m.getUserOneId()+"";
        }

    }


}
