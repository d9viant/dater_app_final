package com.comtrade.broker;

import com.comtrade.domain.GeneralDomain;

import java.util.List;
import java.util.Map;

public interface IBroker {
    void save(GeneralDomain gd);
    void delete(GeneralDomain gd);

    GeneralDomain getFromDb(GeneralDomain gd);

    Map<String, GeneralDomain> getInnerJoinUser(GeneralDomain gd);

    Map<String, List<GeneralDomain>> getInnerJoinList(GeneralDomain gd);

}
