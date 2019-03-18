package com.comtrade.domain;

public class Gender {
    private int userId;
    private char gender;
    private char preferredGender;




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
}
