package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DataStorageClass {
	private Map<String, List<GeneralDomain>> allMatches = new HashMap<>();
	private Map<String, List<GeneralDomain>> allMessages = new HashMap<>();
	private Map<String, GeneralDomain> getAllUserList = new HashMap<>();
	private boolean trigger = true;
	JProgressBar progressBar1;
	JTextArea txtServerLogs;


	public synchronized void getData(JTextArea backupLogs, JProgressBar progressBar) throws InterruptedException {
			ControllerBLogic.getInstance().getAllUsers(getAllUserList);
        	ControllerBLogic.getInstance().getAllMessages(allMessages);
			ControllerBLogic.getInstance().getAllMatches(allMatches);
			System.out.println("data thread notified");

	}



	public synchronized void saveBatch(JTextArea txtServerLogs, JProgressBar progressBar1) throws InterruptedException {
		this.progressBar1=progressBar1;
		this.txtServerLogs=txtServerLogs;

				txtServerLogs.append("\n" + "Backup Started");
				ControllerBLogic.getInstance().saveBatch(getAllUserList);
				txtServerLogs.append("\n" + "Users saved");
				ControllerBLogic.getInstance().saveMatchBatch(allMatches);
				txtServerLogs.append("\n" + "Matches saved");
				ControllerBLogic.getInstance().saveMessageBatch(allMessages);
				txtServerLogs.append("\n" + "Messages saved");
				txtServerLogs.append("\n"+"Backup Done");



	}



	public  Map<String, List<GeneralDomain>> getAllMatches() {
			return allMatches;


	}

	public synchronized Map<String, GeneralDomain> getGetAllUserList() {

		return getAllUserList;


	}

	public void setGetAllUserList(HashMap<String, GeneralDomain> getAllUserList) {
		this.getAllUserList = getAllUserList;
	}

	public Map<String, List<GeneralDomain>> getAllMessages() {
			return allMessages;


	}

	public void setAllMessages(HashMap<String, List<GeneralDomain>> allMessages) {
		this.allMessages = allMessages;
	}


}
