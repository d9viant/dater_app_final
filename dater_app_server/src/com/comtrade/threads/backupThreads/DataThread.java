package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataThread extends Thread {
	private Map<String, List<GeneralDomain>> allMatches = new HashMap<>();
	private Map<String, List<GeneralDomain>> allMessages = new HashMap<>();
	private Map<String, GeneralDomain> getAllUserList = new HashMap<>();
	private boolean transfer = true;

	public void run() {
		try {
			getAllUsers();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private synchronized void getAllUsers() throws InterruptedException {
		ControllerBLogic.getInstance().getAllUsers(getAllUserList);
		getAllMessagesMatches();
		}

	private synchronized void getAllMessagesMatches() {
		ControllerBLogic.getInstance().getAllMessages(allMessages);
		ControllerBLogic.getInstance().getAllMatches(allMatches);


	}

	public Map<String, List<GeneralDomain>> getAllMatches() {
		synchronized (this) {
			return allMatches;
		}

	}

	public Map<String, GeneralDomain> getGetAllUserList() {
		synchronized (this) {
			return getAllUserList;
		}

	}

	public void setGetAllUserList(HashMap<String, GeneralDomain> getAllUserList) {
		this.getAllUserList = getAllUserList;
	}

	public Map<String, List<GeneralDomain>> getAllMessages() {
		synchronized (this) {
			return allMessages;
		}

	}

	public void setAllMessages(HashMap<String, List<GeneralDomain>> allMessages) {
		this.allMessages = allMessages;
	}


}
