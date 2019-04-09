package com.comtrade.controllerBL;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.profil.SO.*;
import com.comtrade.sysops.GeneralSystemOperation;

import java.util.HashMap;
import java.util.List;


public class ControllerBLogic {
    private static final Object mutex = new Object();
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

	public void saveIntoDB(HashMap u) {
		GeneralSystemOperation op = new SaveIntoDatabaseSO();
		op.executeSo(u);
	}

	public void getAllMessages(HashMap<String, HashMap<String, List<GeneralDomain>>> hm) {
		GeneralSystemOperation op = new GetMessagesDBSO();
		op.executeSo(hm);
	}

	public void getAllUsers(HashMap<String, HashMap<String, GeneralDomain>> allUsers) {
		GeneralSystemOperation op = new GetInnerJoinFromUserDBSO();
		op.executeSo(allUsers);

    }

	public void checkProfile(User check) {
		GeneralSystemOperation op = new GetUserDBSO();
		op.executeSo(check);

	}

	public void getUserFromDB(HashMap<String, GeneralDomain> u) {
		GeneralSystemOperation op = new GetUserDBSO();
		op.executeSo(u);
	}

	public void getAllMatches(HashMap<String, HashMap<String, List<GeneralDomain>>> allMatches) {
		GeneralSystemOperation op = new GetMatchesDBSO();
		op.executeSo(allMatches);
	}
}
