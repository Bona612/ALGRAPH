package progetto;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Prova extends Application {

	public static void main(String[] args) {
		
		launch(args);

	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LauncherControl lc = new LauncherControl();
		/*
		utilityButton vbox = new utilityButton(lc.getPane(), lc.getGraph());
		StartWindow sw = new StartWindow (lc.getPane(), vbox.getBox());		
		Scene scene = new Scene(sw.getWindow());
		*/
		primaryStage.setScene(lc.getScene());
		primaryStage.setHeight(800);
		primaryStage.setWidth(1000);
		primaryStage.show();
	
	}
}
