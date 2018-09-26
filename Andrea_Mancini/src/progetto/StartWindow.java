package progetto;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StartWindow {
	
	private BorderPane window;
	private double heightScene = 800;
	private double widthScene = 1000;
	
	

	public StartWindow(Pane root, VBox buttons, Pane menu) {
		window = new BorderPane();
		window.setRight(buttons);
		window.setCenter(root);
		window.setTop(menu);
		window.setPrefHeight(heightScene);
		window.setPrefWidth(widthScene);
		window.setStyle("-fx-border-color: black;  -fx-border-insets: 5; -fx-border-width: 1; -fx-border-radius: 3;");
		
	}
	
	public BorderPane getMenu() {
		return window;
	}
	
	
}