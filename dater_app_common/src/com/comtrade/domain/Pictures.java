package com.comtrade.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Pictures implements GeneralDomain, Serializable {
    @Override
    public List<GeneralDomain> fixSelect(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public String returnTableName() {
        return null;
    }

    @Override
    public String returnTableRows() {
        return null;
    }

    @Override
    public String returnInsertFormat() {
        return null;
    }

    @Override
    public String delete(GeneralDomain gd) {
        return null;
    }
}
