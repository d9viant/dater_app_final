package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.sysops.GeneralSystemOperation;

import java.util.HashMap;
import java.util.Map;

public class GetInnerJoinFromUserDBSO extends GeneralSystemOperation {
    @Override
    public void executeConcreteOperation(Object obj) {
        HashMap<String, GeneralDomain> getAllUserList = (HashMap<String, GeneralDomain>) obj;
        IBroker ib = new Broker();
        Map<String, GeneralDomain> fromDbHm = ib.getInnerJoinUser(new User());
        getAllUserList.putAll(fromDbHm);


    }
}
