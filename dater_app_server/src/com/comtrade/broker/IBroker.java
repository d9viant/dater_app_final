package com.comtrade.broker;

import com.comtrade.domain.GeneralDomain;

import java.util.List;

public interface IBroker {
    void save(GeneralDomain gd);
    void getAll(GeneralDomain gd);
    void delete(GeneralDomain gd);

}
