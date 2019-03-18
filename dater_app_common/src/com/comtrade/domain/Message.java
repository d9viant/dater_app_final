package com.comtrade.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Message implements GeneralDomain{
    private int idMessage;
    private int userOneId;
    private int userTwoId;
    private int senderId;
    private String messageStatus;
    private String sendDate;
    private String sendTime;
    private String receivedDate;
    private String receivedTime;
    private String messageBody;

    public Message(){

    }

    public Message(int idMessage, int userOneId, int userTwoId, int senderId, String messageStatus,
                   String sendDate, String receivedDate, String sendTime, String receivedTime, String messageBody) {

        this.idMessage = idMessage;
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
        this.senderId = senderId;
        this.messageStatus = messageStatus;
        this.sendDate = sendDate;
        this.receivedDate = receivedDate;
        this.sendTime = sendTime;
        this.receivedTime = receivedTime;
        this.messageBody = messageBody;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
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

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public List<GeneralDomain> fixSelect(ResultSet rs) throws SQLException {
        List<GeneralDomain> list = new ArrayList<>();
        while(rs.next()) {
            int idMessage = rs.getInt("idMessage");
            int userOneId= rs.getInt("userOneId");
            int userTwoId = rs.getInt("userTwoId");
            int senderId = rs.getInt("senderId");
            String messageStatus = rs.getString("messageStatus");
            String sendDate = rs.getDate("sendDate").toString();
            String receivedDate = rs.getDate("receivedDate").toString();
            String sendTime = rs.getTime("sendTime").toString();
            String receivedTime = rs.getTime("receivedTime").toString();
            String messageBody = rs.getString("messageBody");
            Message pm = new Message(idMessage, userOneId, userTwoId, senderId, messageStatus, sendDate, receivedDate, sendTime, receivedTime, messageBody);
            list.add(pm);
        }
        return list;
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
}
