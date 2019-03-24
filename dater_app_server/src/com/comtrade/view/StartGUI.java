package com.comtrade.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGUI extends Application {
    private Stage stage;
    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage=primaryStage;
        mainWindow();
    }

    private void mainWindow(){
	    FXMLLoader loader = new FXMLLoader(StartGUI.class.getResource("/com/comtrade/view/serverLayout/server.fxml"));
        try {
            AnchorPane pane= loader.load();
            Scene scene=new Scene(pane);
	        scene.getStylesheets().addAll(StartGUI.class.getResource("application.css").toExternalForm());
            stage.setResizable(false);
            stage.setTitle("Server");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        launch(args);

    }





}
