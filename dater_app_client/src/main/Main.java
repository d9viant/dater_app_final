package main;

import com.comtrade.thread.ProcessingFromServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage stage;
	public Stage getStage() {
		return stage;
	}

	@Override
    public void start(Stage primaryStage) throws Exception{
        Main.stage = primaryStage;
        startTask();
        mainWindow();
    }

    public void startTask() {
        // Create a Runnable
        Runnable task = new Runnable() {
            public void run() {
                runTask();
            }
        };
        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }

    public void runTask() {
        ProcessingFromServer pfs = new ProcessingFromServer();
        pfs.run();
    }


    private void mainWindow(){

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/comtrade/viewLayout/login.fxml"));
        try {
            AnchorPane pane= loader.load();
            Scene scene=new Scene(pane);
            scene.getStylesheets().addAll(Main.class.getResource("application.css").toExternalForm());
            stage.setResizable(false);
            stage.setTitle("Dater Login");
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
