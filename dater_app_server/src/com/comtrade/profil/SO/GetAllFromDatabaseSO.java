package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.*;
import com.comtrade.sysops.GeneralSystemOperation;

import java.util.List;
import java.util.Map;

public class GetAllFromDatabaseSO extends GeneralSystemOperation {
	@Override
	public void executeConcreteOperation(Object obj) {
		Map<String, Object> hm = (Map<String, Object>) obj;
		IBroker ib = new Broker();
		if (hm.containsKey("users")) {
			List<GeneralDomain> users = ib.getAll(new User());
			hm.put("users", users);
		} else if (hm.containsKey("age")) {
			List<GeneralDomain> age = ib.getAll(new Age());
			hm.put("age", age);
		} else if (hm.containsKey("gender")) {
			List<GeneralDomain> gender = ib.getAll(new Gender());
			hm.put("gender", gender);
		} else if (hm.containsKey("locaton")) {
			List<GeneralDomain> location = ib.getAll(new Location());
			hm.put("location", location);
		} else if (hm.containsKey("matches")) {
			List<GeneralDomain> matches = ib.getAll(new Matches());
			hm.put("matches", matches);
		} else if (hm.containsKey("message")) {
			List<GeneralDomain> messages = ib.getAll(new Message());
			hm.put("message", messages);
		} else if (hm.containsKey("pictures")) {
			List<GeneralDomain> pictures = ib.getAll(new Pictures());
			hm.put("pictures", pictures);
		} else if (hm.containsKey("ratings")) {
			List<GeneralDomain> ratings = ib.getAll(new Rating());
			hm.put("ratings", ratings);
		}


	}

//		allData.put("users", null);
//		allData.put("age", null);
//		allData.put("gender", null);
//		allData.put("location", null);
//		allData.put("matches", null);
//		allData.put("message", null);
//		allData.put("pictures", null);
//		allData.put("ratings", null);

}
