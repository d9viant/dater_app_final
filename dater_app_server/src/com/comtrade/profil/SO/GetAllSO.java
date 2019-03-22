package com.comtrade.profil.SO;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.sysops.GeneralSystemOperation;

import java.util.List;
import java.util.Map;

public class GetAllSO extends GeneralSystemOperation {
	@Override
	public void executeConcreteOperation(Object obj) {
		Map<String, Object> hm = (Map<String, Object>) obj;
		IBroker ib = new Broker();
		if (hm.containsKey("users")) {
			List<GeneralDomain> list = ib.getAll(new User());
			hm.put("users", list);
		}


	}
}
