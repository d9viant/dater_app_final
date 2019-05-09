package com.comtrade.geoloc;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import eu.bitm.NominatimReverseGeocoding.Address;
import eu.bitm.NominatimReverseGeocoding.NominatimReverseGeocodingJAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;

public class GeoLoc {
    private NominatimReverseGeocodingJAPI test = new NominatimReverseGeocodingJAPI();
    private Address address;
    private String addressString;
    private File database;
    private DatabaseReader dbReader;
    private ArrayList<Double> red = new ArrayList<>(2);


    public GeoLoc() throws IOException {
        database = new File("C:\\Users\\Strahinja\\IdeaProjects\\dater_app_final\\dater_app_client\\src\\assets\\GeoLite2-City.mmdb");
        dbReader = new DatabaseReader.Builder(database).build();
    }

    public ArrayList<Double> location() throws IOException {
        //Find local IP
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));
        String ip = in.readLine(); //you get the IP as a String
        // GeoLoc using the GeoLite2 Database
        try {
            // Setting IP address
            InetAddress ipAddress = InetAddress.getByName(ip);
            // Reading from the DB and returning latitude and longitude
            try {
                CityResponse response = dbReader.city(ipAddress);
                String latitude = response.getLocation().getLatitude().toString();
                String longitude = response.getLocation().getLongitude().toString();
                double lat = Double.parseDouble(latitude);
                double longi = Double.parseDouble(longitude);
                red.add(0, lat);
                red.add(1, longi);

            } catch (GeoIp2Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Return Coords
        return red;
    }

    //ReverseGeoLoc which returns address + street number
    public String[] ReverseGeoLoc() {
        String[] redStrings;
        // address = (test.getAdress(red.get(0), red.get(1).doubleValue()));
        address = (test.getAdress(red.get(0), red.get(1)));
        addressString = address.getDisplayName();
        redStrings = addressString.split("\\, ");
        return redStrings;
    }
    public void setRed(ArrayList<Double> red) {
        this.red = red;
    }
}


