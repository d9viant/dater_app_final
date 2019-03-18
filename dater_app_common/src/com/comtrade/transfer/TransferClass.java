package com.comtrade.transfer;

import java.io.Serializable;

public class TransferClass implements Serializable {


    private int operation;
    private Object server_object;
    private Object client_object;
    private String server_message;


    public Object getServer_object() {
        return server_object;
    }

    public void setServer_object(Object server_object) {
        this.server_object = server_object;
    }

    public Object getClient_object() {
        return client_object;
    }

    public void setClient_object(Object client_object) {
        this.client_object = client_object;
    }

    public String getServer_message() {
        return server_message;
    }

    public void setServer_message(String server_message) {
        this.server_message = server_message;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }
}
