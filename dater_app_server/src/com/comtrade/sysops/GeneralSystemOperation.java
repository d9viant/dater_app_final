package com.comtrade.sysops;

import com.comtrade.broker.Broker;
import com.comtrade.connection.Connection;

public abstract class GeneralSystemOperation {
    public void executeSo(Object obj) {
        try {
            startTransaction();
            executeConcreteOperation(obj);
            confirmTransaction();
        } catch (Exception e) {
            cancelTransaction();
        } finally {
            closeConnection();
        }
    }

    public abstract void executeConcreteOperation(Object obj);

    private void startTransaction() {
        Connection.getInstance().startTransaction();
    }

    private void confirmTransaction() {
        Connection.getInstance().confirmTransaction();
    }

    private void cancelTransaction() {
        Connection.getInstance().cancelTransaction();
    }

    private void closeConnection() {
        Connection.getInstance().closeConnection();
    }
}