package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Location implements GeneralDomain, Serializable {
    private int userID;
    private double longitude;
    private double latitude;
    private String address;
    private static int prefferedDistance = 50;


    public Location(){

    }
    public Location(double longitude, double latitude) {
	    this.longitude=longitude;
	    this.latitude=latitude;
	}

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public List<GeneralDomain> fixSelect(ResultSet rs) throws SQLException {
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
}