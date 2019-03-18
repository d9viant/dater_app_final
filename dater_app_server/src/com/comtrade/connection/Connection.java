package com.comtrade.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static Connection instance;



    private java.sql.Connection conn;
    private Connection(){

    }

    public static Connection getInstance(){
        if(instance == null){
            instance = new Connection();
        }
        return instance;
    }

    public void startTransaction() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dater_app?useUnicode=true&characterEncoding=utf-8", "root", "");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void confirmTransaction() {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelTransaction() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public java.sql.Connection getConn() {
        return conn;
    }

}
