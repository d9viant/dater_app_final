package com.comtrade.threads;

import com.comtrade.domain.Exception;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server extends Thread {
    private JTextArea txtServerLogs;

    public Server(JTextArea txtServerLogs) {
		this.txtServerLogs = txtServerLogs;
	}

	public void run() {
		try {
			startServer();

		} catch (IOException e) {
			try {
				throw new Exception("Server did not start");
			} catch (Exception exception) {
				
				exception.printStackTrace();
			}
		}
	}

	private void startServer() throws IOException {
        ServerSocket ss = new ServerSocket(9500);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        txtServerLogs.setText("Server Started" + " " + "at" + " " + sdf.format(new Date()));
		while (true) {
			Socket socket = ss.accept();
			ClientThread ct = new ClientThread();
			ct.setSocket(socket);
			ct.start();

		}

	}
}
