package main;

import com.comtrade.thread.processingFromServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;





public class Main extends Application {

    private Stage stage;	
	public Stage getStage() {
		return stage;
	}

	@Override
    public void start(Stage primaryStage) throws Exception{
      this.stage=primaryStage;
      mainWindow();
      startListeningFromServer();
    }

    private void startListeningFromServer() {
        processingFromServer pfs = new processingFromServer();
        pfs.run();
    }

    private void mainWindow(){

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/comtrade/viewLayout/login.fxml"));
        try {
            AnchorPane pane= loader.load();
            Scene scene=new Scene(pane);
            scene.getStylesheets().addAll(Main.class.getResource("application.css").toExternalForm());
            stage.setResizable(false);
            stage.setTitle("Dater");
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