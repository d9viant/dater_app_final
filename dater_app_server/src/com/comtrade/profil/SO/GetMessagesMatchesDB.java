package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.Matches;
import com.comtrade.domain.Message;
import com.comtrade.sysops.GeneralSystemOperation;

import java.util.List;
import java.util.Map;

import static com.comtrade.domain.Constants.MATCHES;
import static com.comtrade.domain.Constants.MESSAGE;


public class GetMessagesMatchesDB extends GeneralSystemOperation {
    @Override
    public void executeConcreteOperation(Object obj) {
        Map<String, Object> hm = (Map<String, Object>) obj;
        IBroker ib = new Broker();
        if (hm.containsKey(MATCHES)) {
            List<GeneralDomain> matches = ib.getAll(new Matches());
            hm.put(MATCHES, matches);
        } else if (hm.containsKey(MESSAGE)) {
            List<GeneralDomain> message = ib.getAll(new Message());
            hm.put(MESSAGE, message);
        }


    }


}
