package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.Message;
import com.comtrade.sysops.GeneralSystemOperation;

import java.util.HashMap;
import java.util.List;

import static com.comtrade.domain.Constants.MESSAGES;


public class GetMessagesDBSO extends GeneralSystemOperation {
    @Override
    public void executeConcreteOperation(Object obj) {
        HashMap<String, HashMap<String, List<GeneralDomain>>> hm = (HashMap<String, HashMap<String, List<GeneralDomain>>>) obj;
        IBroker ib = new Broker();
        HashMap<String, List<GeneralDomain>> allMessages = ib.getInnerJoinList(new Message());
        hm.put(MESSAGES, allMessages);





    }


}
