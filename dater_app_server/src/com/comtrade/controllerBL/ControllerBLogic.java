package com.comtrade.controllerBL;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.profil.SO.*;
import com.comtrade.sysops.GeneralSystemOperation;
import com.comtrade.threads.ClientThread;
import com.comtrade.threads.backupThreads.DataThread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ControllerBLogic {
    private static final Object mutex = new Object();
	private static ControllerBLogic instance;
    private Map<String, ClientThread> connectedUserMap = new HashMap<>();
    private  DataThread datathread;


	private ControllerBLogic() {
		datathread = new DataThread();
		datathread.start();
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

	public void saveBatch(Map<String, GeneralDomain> u) {
		GeneralSystemOperation op = new BatchUserSO();
		op.executeSo(u);
	}

	public void saveMessageBatch(Map<String, List<GeneralDomain>> u) {
		GeneralSystemOperation op = new BatchUserSO();
		op.executeSo(u);
	}

	public void saveMatchBatch(Map<String, List<GeneralDomain>> u) {
		GeneralSystemOperation op = new BatchUserSO();
		op.executeSo(u);
	}

	public void getAllMessages(Map<String, List<GeneralDomain>> hm) {
		GeneralSystemOperation op = new GetMessagesDBSO();
		op.executeSo(hm);
	}

	public void getAllUsers(Map<String, GeneralDomain> allUsersHm) {
		GeneralSystemOperation op = new GetInnerJoinFromUserDBSO();
        op.executeSo(allUsersHm);

    }

	public void checkProfile(User check) {
		GeneralSystemOperation op = new GetUserDBSO();
		op.executeSo(check);

	}

	public void getUserFromDB(HashMap<String, GeneralDomain> u) {
		GeneralSystemOperation op = new GetUserDBSO();
		op.executeSo(u);
	}

	public void getAllMatches(Map<String, List<GeneralDomain>> allMatches) {
		GeneralSystemOperation op = new GetMatchesDBSO();
		op.executeSo(allMatches);
	}


    public void insertIntoActive(String s, ClientThread thread) {
        connectedUserMap.put(s, thread);

    }

	public DataThread getDatathread() {
		return datathread;
	}
    public void removeActiveUser(String currentUsername) {
        connectedUserMap.remove(currentUsername);
    }
}
