package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.sysops.GeneralSystemOperation;

import java.sql.SQLException;
import java.util.HashMap;
public class BatchUserSO extends GeneralSystemOperation {
    @Override
    public void executeConcreteOperation(Object obj) throws SQLException {
        HashMap<String, GeneralDomain> ul = (HashMap<String, GeneralDomain>) obj;
        IBroker ib = new Broker();
        ib.save(ul);
    }
}
