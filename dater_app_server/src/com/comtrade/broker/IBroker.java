package com.comtrade.broker;

import com.comtrade.domain.GeneralDomain;

import java.util.HashMap;
import java.util.List;

public interface IBroker {
    void save(GeneralDomain gd);
    void delete(GeneralDomain gd);

    GeneralDomain getFromDb(GeneralDomain gd);

    HashMap<String, GeneralDomain> getInnerJoinUser(GeneralDomain gd);

    HashMap<String, List<GeneralDomain>> getInnerJoinList(GeneralDomain gd);

}
