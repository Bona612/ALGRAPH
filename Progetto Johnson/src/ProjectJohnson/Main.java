package ProjectJohnson;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		LauncherControl lc = new LauncherControl();
		lc.getScene().getStylesheets().addAll(this.getClass().getResource("../CssStyle/sfondo.css").toExternalForm());
		
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../icon/AlgraphIcon.png")));
		primaryStage.setScene(lc.getScene());
		primaryStage.setHeight(850);
		primaryStage.setWidth(1050);
		primaryStage.show();
	}
}
