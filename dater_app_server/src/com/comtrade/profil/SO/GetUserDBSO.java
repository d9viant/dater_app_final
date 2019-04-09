package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.sysops.GeneralSystemOperation;

import java.util.HashMap;

import static com.comtrade.domain.Constants.USER;

public class GetUserDBSO extends GeneralSystemOperation {
    @Override
    public void executeConcreteOperation(Object obj) {
        IBroker ib = new Broker();
        HashMap<String, GeneralDomain> hm = (HashMap<String, GeneralDomain>) obj;
        User u = (User) hm.get(USER);
        hm.put(USER, ib.getFromDb(u));
    }
}
