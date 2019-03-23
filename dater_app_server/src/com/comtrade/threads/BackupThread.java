package com.comtrade.threads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;

import java.util.HashMap;
import java.util.List;

public class BackupThread extends Thread{
	private static Object mutex = new Object();
	private HashMap<String, List<GeneralDomain>> allData = new HashMap<>();
	private HashMap<String, List<GeneralDomain>> users = new HashMap<>();

	public void run() {
		getAll();

	}

	private void getAll() {
		allData.put("users", null);
		allData.put("age", null);
		allData.put("gender", null);
		allData.put("location", null);
		allData.put("matches", null);
		allData.put("message", null);
		allData.put("pictures", null);
		allData.put("ratings", null);


		ControllerBLogic.getInstance().getAll(allData);

	}


}
