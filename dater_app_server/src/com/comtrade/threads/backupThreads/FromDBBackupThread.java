package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;

import java.util.HashMap;
import java.util.List;

public class FromDBBackupThread extends Thread {
	private HashMap<String, List<GeneralDomain>> allMessagesMatches = new HashMap<>();
	private HashMap<String, GeneralDomain> getAllUserList = new HashMap<>();

	public void run() {
		getAllUsers();
		getAllMessagesMatches();


	}

	private void getAllUsers() {
		ControllerBLogic.getInstance().getAllUsers(getAllUserList);
	}

	private void getAllMessagesMatches() {
		ControllerBLogic.getInstance().getAllMessagesMatches(allMessagesMatches);

	}

	public HashMap<String, List<GeneralDomain>> getAllMessagesPicturesMatches() {
		synchronized (this) {
			return allMessagesMatches;
		}

	}

	public HashMap<String, GeneralDomain> getGetAllUserList() {
		synchronized (this) {
			return getAllUserList;
		}

	}

	public void setGetAllUserList(HashMap<String, GeneralDomain> getAllUserList) {
		this.getAllUserList = getAllUserList;
	}
}
