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


	public void run() {
		getAllUsers();
		User u = (User) getAllUserList.get("keseljs");
		System.out.println(u.getFirstName());
//		getAllMessagesMatches();
//		notifyAll();

	}


	private void getAllUsers() {
		ControllerBLogic.getInstance().getAllUsers(getAllUserList);
	}

	private void getAllMessagesMatches() {
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
