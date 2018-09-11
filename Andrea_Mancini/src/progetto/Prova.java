package progetto;

import javafx.application.Application;



import javafx.stage.Stage;


public class Prova extends Application {

	public static void main(String[] args) {
		
		launch(args);

	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LauncherControl lc = new LauncherControl();
		
		primaryStage.setScene(lc.getScene());
		primaryStage.sizeToScene();
		primaryStage.show();
	
	}
}

