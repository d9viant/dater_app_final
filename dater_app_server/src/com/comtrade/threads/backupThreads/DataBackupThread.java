package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBackupThread extends Thread {
	private Map<String, List<GeneralDomain>> allMatches = new HashMap<>();
	ObservableMap<String, List<GeneralDomain>> allMatchesObserver = FXCollections.observableMap(allMatches);
	private Map<String, List<GeneralDomain>> allMessages = new HashMap<>();
	ObservableMap<String, List<GeneralDomain>> allMessageObserver = FXCollections.observableMap(allMessages);
	private Map<String, GeneralDomain> getAllUserList = new HashMap<>();
	ObservableMap<String, GeneralDomain> allUsersObserver = FXCollections.observableMap(getAllUserList);

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
