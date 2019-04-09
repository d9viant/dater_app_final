package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.*;
import com.comtrade.sysops.GeneralSystemOperation;

import java.util.Map;

import static com.comtrade.domain.Constants.*;

public class SaveIntoDatabaseSO extends GeneralSystemOperation {
    @Override
    public void executeConcreteOperation(Object obj) {
        IBroker ib = new Broker();
        Map<String, Object> hm = (Map<String, Object>) obj;
        if (hm.containsKey(USER)) {
            User u = (User) hm.get(USER);
            ib.save(u);
        } else if (hm.containsKey(AGE)) {
            Age a = (Age) hm.get(AGE);
            ib.save(a);
        } else if (hm.containsKey(GENDER)) {
            Gender g = (Gender) hm.get(GENDER);
            ib.save(g);
        } else if (hm.containsKey(LOCATION)) {
            Location l = (Location) hm.get(LOCATION);
            ib.save(l);
        } else if (hm.containsKey(MATCHES)) {
            Matches m = (Matches) hm.get(MATCHES);
            ib.save(m);
        } else if (hm.containsKey(MESSAGES)) {
            Message msg = (Message) hm.get(MESSAGES);
            ib.save(msg);
        } else if (hm.containsKey(PICTURES)) {
            Pictures p = (Pictures) hm.get(PICTURES);
            ib.save(p);
        } else if (hm.containsKey(RATING)) {
            Rating r = (Rating) hm.get(RATING);
            ib.save(r);

        }
    }
}
