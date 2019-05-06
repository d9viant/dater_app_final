package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.sysops.GeneralSystemOperation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class BatchMessage extends GeneralSystemOperation {
    @Override
    public void executeConcreteOperation(Object obj) {
        HashMap<String, List<GeneralDomain>> ul = (HashMap<String, List<GeneralDomain>>) obj;
        IBroker ib = new Broker();
        try {
            ib.saveListMessage(ul);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
