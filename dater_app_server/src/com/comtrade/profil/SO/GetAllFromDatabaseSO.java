package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.*;
import com.comtrade.sysops.GeneralSystemOperation;

import java.util.List;
import java.util.Map;

import static com.comtrade.domain.Constants.*;
public class GetAllFromDatabaseSO extends GeneralSystemOperation {
	@Override
	public void executeConcreteOperation(Object obj) {
		Map<String, Object> hm = (Map<String, Object>) obj;
		IBroker ib = new Broker();
		if (hm.containsKey(USER)) {
			List<GeneralDomain> users = ib.getAll(new User());
			hm.put(USER, users);
		} else if (hm.containsKey(AGE)) {
			List<GeneralDomain> age = ib.getAll(new Age());
			hm.put(AGE, age);
		} else if (hm.containsKey(GENDER)) {
			List<GeneralDomain> gender = ib.getAll(new Gender());
			hm.put(GENDER, gender);
		} else if (hm.containsKey(LOCATION)) {
			List<GeneralDomain> location = ib.getAll(new Location());
			hm.put(LOCATION, location);
		} else if (hm.containsKey(MATCHES)) {
			List<GeneralDomain> matches = ib.getAll(new Matches());
			hm.put(MATCHES, matches);
		} else if (hm.containsKey(MESSAGE)) {
			List<GeneralDomain> messages = ib.getAll(new Message());
			hm.put(MESSAGE, messages);
		} else if (hm.containsKey(PICTURES)) {
			List<GeneralDomain> pictures = ib.getAll(new Pictures());
			hm.put(PICTURES, pictures);
		} else if (hm.containsKey(RATING)) {
			List<GeneralDomain> ratings = ib.getAll(new Rating());
			hm.put(RATING, ratings);
		}


	}


}
