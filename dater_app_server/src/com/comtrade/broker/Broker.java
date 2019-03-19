package com.comtrade.broker;

import com.comtrade.connection.Connection;
import com.comtrade.domain.GeneralDomain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Broker implements IBroker{
    private static Broker instance;
    public Broker() {

    }


    public static Broker getInstance() {
        if (instance == null) {
            instance = new Broker();
        }
        return instance;
    }



    public void save(GeneralDomain gd) {
        String query = "INSERT INTO " + gd.returnTableName() + " " + gd.returnTableRows() + " " + gd.returnInsertFormat();
        try {
            Statement st = Connection.getInstance().getConn().createStatement();
            st.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void getAll(GeneralDomain gd) {
        String query = "SELECT * FROM "+gd.returnTableName();
        try {
            Statement st = Connection.getInstance().getConn().createStatement();
            ResultSet rs = st.executeQuery(query);
            switch(gd.returnTableName()){
                case "user":
                    allUsers = gd.fixSelect(rs);
                    break;
                case "matchtable":
                    allMatches=gd.fixSelect(rs);
                    break;
                case "age":
                    allAges=gd.fixSelect(rs);
                    break;
                case "location":
                    allLocations=gd.fixSelect(rs);
                    break;
                case "gender":
                    allGenders=gd.fixSelect(rs);
                    break;
                case "chatmessage":
                    allMessages=gd.fixSelect(rs);
                    break;
                case "pictures":
                    allPictures=gd.fixSelect(rs);
                    break;
                case "rating":
                    allRatings=gd.fixSelect(rs);
                    break;

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void delete(GeneralDomain gd) {
        String statement = "DELETE FROM "+gd.returnTableName()+gd.delete(gd);
        try {
            Statement st = Connection.getInstance().getConn().createStatement();
            st.executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
