package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;

import java.util.HashMap;
import java.util.List;

public class DataBackupThread extends Thread {
	private HashMap<String, List<GeneralDomain>> allMatches = new HashMap<>();
	private HashMap<String, List<GeneralDomain>> allMessages = new HashMap<>();
	private HashMap<String, GeneralDomain> getAllUserList = new HashMap<>();

	public void run() {
		backupToDb();
		getAllUsers();
		getAllMessagesMatches();
		notifyAll();
	}

	private void backupToDb() {





	}

	private void getAllUsers() {
		ControllerBLogic.getInstance().getAllUsers(getAllUserList);
	}

	private void getAllMessagesMatches() {
		ControllerBLogic.getInstance().getAllMessages(allMessages);
		ControllerBLogic.getInstance().getAllMatches(allMatches);

	}

	public HashMap<String, List<GeneralDomain>> getAllMatches() {
		synchronized (this) {
			return allMatches;
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

	public HashMap<String, List<GeneralDomain>> getAllMessages() {
		synchronized (this) {
			return allMessages;
		}

	}

	public void setAllMessages(HashMap<String, List<GeneralDomain>> allMessages) {
		this.allMessages = allMessages;
	}


}
