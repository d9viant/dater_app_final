package com.comtrade.view;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.threads.Server;
import com.comtrade.threads.ServerTimeThread;
import com.comtrade.threads.backupThreads.FromDBBackupThread;
import com.comtrade.threads.backupThreads.UserGeneratorThread;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerView implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private Button btnStartServer;

    @FXML
    private Button btnRestartServer;

    @FXML
    private Button btnManualBackup;

    @FXML
    private Button btnBackupFrequency;

    @FXML
    private TextField timeText;


    @FXML
    private ComboBox<String> backupComboBox;

    @FXML
    private TextArea txtServerLogs;

    @FXML
    private TextArea txtBackupLogs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.setImplicitExit(false);
        backupComboBoxIntitialize();
        btnStartServer.setOnAction(event -> {
            startServer();
            getData();

        });

    }

    private void getData() {
        IBroker ib = new Broker();

    }

    //Starts main server threads and all DB threadsster
    protected void startServer() {
        FromDBBackupThread fdbbt = new FromDBBackupThread();
        Server s = new Server(txtServerLogs);
        ServerTimeThread stt = new ServerTimeThread(timeText);
        UserGeneratorThread ugt = new UserGeneratorThread();
        fdbbt.start();
        ugt.start();
        s.start();
        stt.start();


        btnStartServer.setDisable(true);
    }

    private void backupComboBoxIntitialize() {

        backupComboBox.getItems().addAll(
                "Daily",
                "Weekly",
                "Monthly"
        );
        backupComboBox.setValue("Weekly");

    }
}
