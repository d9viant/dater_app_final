package com.comtrade.view;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.threads.Server;
import com.comtrade.threads.ServerTimeThread;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
public class serverView implements Initializable {

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

    protected void startServer() {

        Server s = new Server(txtServerLogs);
        ServerTimeThread stt = new ServerTimeThread(timeText);
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
