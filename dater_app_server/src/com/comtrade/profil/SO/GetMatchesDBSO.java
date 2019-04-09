package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.Matches;
import com.comtrade.sysops.GeneralSystemOperation;

import java.util.HashMap;
import java.util.List;

import static com.comtrade.domain.Constants.MATCHES;

public class GetMatchesDBSO extends GeneralSystemOperation {
    @Override
    public void executeConcreteOperation(Object obj) {
        HashMap<String, HashMap<String, List<GeneralDomain>>> hm = (HashMap<String, HashMap<String, List<GeneralDomain>>>) obj;
        IBroker ib = new Broker();
        HashMap<String, List<GeneralDomain>> allMatches = ib.getInnerJoinList(new Matches());
        hm.put(MATCHES, allMatches);
    }

}
