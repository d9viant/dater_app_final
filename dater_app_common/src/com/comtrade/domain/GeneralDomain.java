package com.comtrade.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface GeneralDomain {
    List<GeneralDomain> fixSelect(ResultSet rs) throws SQLException;

    String returnTableName();

    String returnTableRows();

    String returnInsertFormat();

    String delete(GeneralDomain gd);

    HashMap<String, GeneralDomain> fixInnerSelect(ResultSet rs) throws SQLException;
}
