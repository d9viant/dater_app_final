package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.sysops.GeneralSystemOperation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.comtrade.domain.Constants.DBWRITTEN;
import static com.comtrade.domain.Constants.RDYFORDB;
public class BatchUserSO extends GeneralSystemOperation {
    @Override
    public void executeConcreteOperation(Object obj) throws SQLException {
        HashMap<String, GeneralDomain> ul = (HashMap<String, GeneralDomain>) obj;
        IBroker ib = new Broker();

        ib.save(ul);
//        for (Map.Entry<String, GeneralDomain> entry : ul.entrySet()) {
//            User u = (User) entry.getValue();
//            if(u.getReadyForSql() == RDYFORDB){
//                u.setReadyForSql(DBWRITTEN);
//            }else if(u.getGender().getReadyForSql() == RDYFORDB){
//                u.getGender().setReadyForSql(DBWRITTEN);
//            }else if(u.getRating().getReadyForSql() == RDYFORDB){
//                u.getRating().setReadyForSql(DBWRITTEN);
//            }else if(u.getLocation().getReadyForSql() == RDYFORDB){
//                u.getLocation().setReadyForSql(DBWRITTEN);
//            }else if(u.getAge().getReadyForSql() == RDYFORDB){
//                u.getAge().setReadyForSql(DBWRITTEN);
//            }
//
//        }


    }
}
