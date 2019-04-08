package com.comtrade.controllerBL;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.profil.SO.GetMessagesMatchesDB;
import com.comtrade.profil.SO.SaveIntoDatabaseSO;
import com.comtrade.sysops.GeneralSystemOperation;
import com.comtrade.threads.ClientThread;
import com.comtrade.threads.backupThreads.FromDBBackupThread;
import com.comtrade.transfer.TransferClass;

import java.util.HashMap;
import java.util.List;

import static com.comtrade.domain.Constants.USER;
import static com.comtrade.domain.Constants.USERNAME_TAKEN;



public class ControllerBLogic {
    private static final Object mutex = new Object();
	private static ControllerBLogic instance;
	private ControllerBLogic() {

	}

	public static ControllerBLogic getInstance() {
		ControllerBLogic result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null) {
					result = instance = new ControllerBLogic();
				}
			}

		}
		return instance;
	}

	public void saveIntoDB(HashMap u) {
		GeneralSystemOperation op = new SaveIntoDatabaseSO();
		op.executeSo(u);
	}

    public void getAllMessagesMatches(HashMap<String, List<GeneralDomain>> hm) {
        GeneralSystemOperation op = new GetMessagesMatchesDB();
		op.executeSo(hm);
	}

    public void getAllUsers(HashMap<String, GeneralDomain> allUsers) {

    }

	public void checkProfile(User check) {
		TransferClass tf = new TransferClass();
		FromDBBackupThread dbBackupThread = new FromDBBackupThread();
        List<GeneralDomain> users = dbBackupThread.getAllMessagesPicturesMatches().get(USER);
		for (GeneralDomain u : users) {
			User user = (User) u;
			if (user.getUsername().equalsIgnoreCase(check.getUsername())) {
				tf.setOperation(USERNAME_TAKEN);
                ClientThread ct = new ClientThread();
                ct.sendToClient(tf);
			}
		}



	}

}
