package com.comtrade.broker;

import com.comtrade.domain.GeneralDomain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IBroker {
    void save(Map<String, GeneralDomain> asd) throws SQLException;
    void saveListMessage(Map<String, List<GeneralDomain>> asd) throws SQLException;
    void saveListMatch(Map<String, List<GeneralDomain>> asd) throws SQLException;
    void delete(GeneralDomain gd);

    GeneralDomain getFromDb(GeneralDomain gd);

    Map<String, GeneralDomain> getInnerJoinUser(GeneralDomain gd);

    Map<String, List<GeneralDomain>> getInnerJoinList(GeneralDomain gd);

}
