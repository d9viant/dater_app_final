package com.comtrade.controllerBL;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.profil.SO.GetAllFromDatabaseSO;
import com.comtrade.profil.SO.SaveIntoDatabaseSO;
import com.comtrade.sysops.GeneralSystemOperation;

import java.util.HashMap;
import java.util.List;


public class ControllerBLogic {
	private static Object mutex = new Object();
	private static ControllerBLogic instance;

	private ControllerBLogic() {

	}

	public static ControllerBLogic getInstance() {
		ControllerBLogic result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null) {
					result = instance = new ControllerBLogic();
				}
			}

		}
		return instance;
	}

	public void saveProfile(User u) {
		GeneralSystemOperation op = new SaveIntoDatabaseSO();
		op.executeSo(u);
	}

	public void getAll(HashMap<String, List<GeneralDomain>> hm) {
		GeneralSystemOperation op = new GetAllFromDatabaseSO();
		op.executeSo(hm);
	}

	public void checkProfile(Boolean check) {

	}

}
