package com.comtrade.domain;
import java.lang.*;
public class Location {
    private int userID;
    private double longitude;
    private double latitude;
    private String address;
    private int prefferedDistance;


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
}