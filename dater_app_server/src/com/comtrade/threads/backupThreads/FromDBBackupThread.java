package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;

import java.util.HashMap;
import java.util.List;

public class FromDBBackupThread extends Thread {
	private HashMap<String, List<GeneralDomain>> allData = new HashMap<>();
	private HashMap<String, List<GeneralDomain>> users = new HashMap<>();
	private HashMap<String, GeneralDomain> getAllUserList = new HashMap<>();

	public void run() {
		getAll();
	}

	private void getAll() {
		ControllerBLogic.getInstance().getAllFromDB(allData);

	}

	public HashMap<String, List<GeneralDomain>> getAllData() {
		synchronized (this) {
			return allData;
		}

	}

}
