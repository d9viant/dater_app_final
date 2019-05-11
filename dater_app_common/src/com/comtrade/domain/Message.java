package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.comtrade.domain.Constants.DBWRITTEN;
import static com.comtrade.domain.Constants.RDYFORDB;

public class Message implements GeneralDomain, Serializable {
    private int idMessage;
    private String usernameOne;
    private String usernameTwo;
    private String senderUsername;
    private String messageStatus;
    private String messageBody;
    private Timestamp messagetime;
    private int readyForSql = DBWRITTEN;

    public Message(){

    }

    public Message(int idMessage, String userOneId, String userTwoId, String senderId,Timestamp messagetime, String messageStatus, String messageBody) {

        this.idMessage = idMessage;
        this.usernameOne = userOneId;
        this.usernameTwo = userTwoId;
        this.senderUsername = senderId;
        this.messagetime = messagetime;
        this.messageStatus = messageStatus;
        this.messageBody = messageBody;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public String getUserOneId() {
        return usernameOne;
    }

    public void setUserOneId(String userOneId) {
        this.usernameOne = userOneId;
    }

    public String getUserTwoId() {
        return usernameTwo;
    }

    public void setUserTwoId(String userTwoId) {
        this.usernameTwo = userTwoId;
    }

    public String getSenderId() {
        return senderUsername;
    }

    public void setSenderId(String senderId) {
        this.senderUsername = senderId;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }


    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Timestamp getMessagetime() {
        return messagetime;
    }

    public void setMessagetime(Timestamp messagetime) {
        this.messagetime = messagetime;
    }

    @Override
    public GeneralDomain fixSelect(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public String returnInnerJoin() {
        return "SELECT * FROM chatmessage INNER JOIN user ON (user.username=chatmessage.usernameOne OR user.username=chatmessage.usernameTwo) ORDER BY messageTime ASC";
    }


    @Override
    public String getForSelectForSpecific(GeneralDomain u) {
        User user = (User) u;
        return " WHERE usernameOne=" + user.getUsername() + " or usernameTwo=" + user.getUsername();
    }


    @Override
    public String returnTableName() {
        return "chatmessage";
    }

    @Override
    public String returnTableRows() {
        return " (usernameOne, usernameTwo, senderUsername, messageTime, messaegeStatus, messageBody) ";
    }

    @Override
    public String returnInsertFormat() {
        return "VALUES ('" + usernameOne + "','" + usernameTwo + "','" + senderUsername + "','" + messagetime + "','" + messageStatus + "','" + messageBody +  "')";
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

        HashMap<String, List<GeneralDomain>> messageHM = new HashMap<>();
        try {
            while (rs.next()) {
                String username = rs.getString("username").toLowerCase();
                String userOneId = rs.getString("usernameOne");
                String userTwoId = rs.getString("usernameTwo");
                String senderId = rs.getString("senderUsername");
                String messageStatus = rs.getString("messaegeStatus");
                Timestamp messagetime = rs.getTimestamp("messageTime");
                String messageBody = rs.getString("messageBody");
                Message m = new Message(idMessage, userOneId, userTwoId, senderId, messagetime, messageStatus, messageBody);
                if (messageHM.containsKey(username)) {
                    messageHM.get(username).add(m);
                } else {
                    List<GeneralDomain> messageList = new ArrayList<>();
                    messageHM.put(username, messageList);
                    messageHM.get(username).add(m);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageHM;
    }

    @Override
    public String returnUserName() {
        return null;
    }


    public int getReadyForSql() {
        return readyForSql;
    }

    public void setReadyForSql(int readyForSql) {
        this.readyForSql = readyForSql;
    }
}
