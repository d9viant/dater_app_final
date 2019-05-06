package com.comtrade.threads.backupThreads;

import com.comtrade.controllerBL.ControllerBLogic;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class DataStorageClass{
	private Map<String, List<GeneralDomain>> allMatches = new HashMap<>();
	private Map<String, List<GeneralDomain>> allMessages = new HashMap<>();
	private Map<String, GeneralDomain> getAllUserList = new HashMap<>();
	private boolean trigger = true;
	JProgressBar progressBar1;
	JTextArea txtServerLogs;


	public synchronized void getData(JTextArea backupLogs, JProgressBar progressBar) throws InterruptedException {

			Thread.sleep(2000);
			ControllerBLogic.getInstance().getAllUsers(getAllUserList);

			progressBar.setValue(25);
			Thread.sleep(2000);
			ControllerBLogic.getInstance().getAllMessages(allMessages);
			progressBar.setValue(50);
			Thread.sleep(2000);
			ControllerBLogic.getInstance().getAllMatches(allMatches);
			progressBar.setValue(75);
			Thread.sleep(2000);
			progressBar.setValue(100);
			Thread.sleep(1000);
			progressBar.setValue(0);
			System.out.println("data thread notified");
			notifyAll();
	}



	public synchronized void saveBatch(JTextArea txtServerLogs, JProgressBar progressBar1) throws InterruptedException {
		this.progressBar1=progressBar1;
		this.txtServerLogs=txtServerLogs;
		wait();
		System.out.println("backup thread notified");
		try {
			while(trigger){
				Thread.sleep(600000);
				txtServerLogs.append("\n" + "Backup Started");
				Thread.sleep(10000);
				ControllerBLogic.getInstance().saveBatch(getAllUserList);
				txtServerLogs.append("\n" + "Users saved");
				progressBar1.setValue(25);
				Thread.sleep(10000);
				ControllerBLogic.getInstance().saveMatchBatch(allMatches);
				txtServerLogs.append("\n" + "Matches saved");
				progressBar1.setValue(50);
				Thread.sleep(10000);
				ControllerBLogic.getInstance().saveMessageBatch(allMessages);
				txtServerLogs.append("\n" + "Messages saved");
				progressBar1.setValue(75);
				Thread.sleep(10000);
				progressBar1.setValue(100);
				txtServerLogs.append("\n"+"Backup Done");
				Thread.sleep(2000);
				progressBar1.setValue(0);

			}




		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}



	public synchronized Map<String, List<GeneralDomain>> getAllMatches() {
			return allMatches;


	}

	public synchronized Map<String, GeneralDomain> getGetAllUserList() {
		System.out.println(getAllUserList.size() + "na kraju");
			return getAllUserList;


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
