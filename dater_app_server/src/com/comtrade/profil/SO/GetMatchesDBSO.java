package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.Matches;
import com.comtrade.sysops.GeneralSystemOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMatchesDBSO extends GeneralSystemOperation {
    @Override
    public void executeConcreteOperation(Object obj) {
        HashMap<String, List<GeneralDomain>> allMatches = (HashMap<String, List<GeneralDomain>>) obj;
        IBroker ib = new Broker();
        Map<String, List<GeneralDomain>> allMatchesFromDb = ib.getInnerJoinList(new Matches());
        allMatches.putAll(allMatchesFromDb);
    }

}
