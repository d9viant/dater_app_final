package com.comtrade.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface GeneralDomain {
    GeneralDomain fixSelect(ResultSet rs) throws SQLException;

    String returnInnerJoin();

    String returnTableName();

    String returnTableRows();

    String returnInsertFormat(GeneralDomain gd);

    String delete(GeneralDomain gd);

    HashMap<String, GeneralDomain> fixInnerSelect(ResultSet rs) throws SQLException;

    HashMap<String, List<GeneralDomain>> fixInnerSelectList(ResultSet rs) throws SQLException;

    String returnUserName(GeneralDomain gd);
}
