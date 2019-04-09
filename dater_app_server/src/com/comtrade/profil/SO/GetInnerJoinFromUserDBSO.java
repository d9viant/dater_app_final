package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.sysops.GeneralSystemOperation;

import java.util.HashMap;

import static com.comtrade.domain.Constants.USER;

public class GetInnerJoinFromUserDBSO extends GeneralSystemOperation {
    @Override
    public void executeConcreteOperation(Object obj) {
        HashMap<String, HashMap<String, GeneralDomain>> getAllUserList = (HashMap<String, HashMap<String, GeneralDomain>>) obj;
        IBroker ib = new Broker();


        HashMap<String, GeneralDomain> hm = ib.getInnerJoinUser(new User());

        getAllUserList.put(USER, ib.getInnerJoinUser(new User()));
    }
}
