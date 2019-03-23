package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.User;
import com.comtrade.sysops.GeneralSystemOperation;

public class SaveIntoDatabaseSO extends GeneralSystemOperation {
    @Override
    public void executeConcreteOperation(Object obj) {
        User user = (User) obj;
        IBroker ib = new Broker();
        ib.save(user);
    }
}
