package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;

import java.util.HashMap;
import java.util.List;

import static com.comtrade.domain.Constants.*;

public class FromDBBackupThread extends Thread {
	private static Object mutex = new Object();


	private HashMap<String, List<GeneralDomain>> allData = new HashMap<>();
	private HashMap<String, List<GeneralDomain>> users = new HashMap<>();

	public void run() {
		getAll();

	}

	private void getAll() {
		allData.put(USER, null);
		allData.put(AGE, null);
		allData.put(GENDER, null);
		allData.put(LOCATION, null);
		allData.put(MATCHES, null);
		allData.put(MESSAGE, null);
		allData.put(PICTURES, null);
		allData.put(RATING, null);
		ControllerBLogic.getInstance().getAllFromDB(allData);

	}

	public synchronized HashMap<String, List<GeneralDomain>> getAllData() {
		return allData;
	}

}
