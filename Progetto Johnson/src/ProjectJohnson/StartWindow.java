package ProjectJohnson;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StartWindow {
	
	private BorderPane window;
	private double heightScene = 800;
	private double widthScene = 1000;
	
	

	public StartWindow(Pane root, VBox buttons, Pane menu) {
		window = new BorderPane();
		window.setRight(buttons);	
		window.getRight().setTranslateX(-128);
		window.getRight().setTranslateY(156);
		window.getRight().setStyle("-fx-border-color: black; -fx-border-insets: 5; -fx-border-width: 1; -fx-border-radius: 3;");
		
		window.setCenter(root);
		window.getCenter().setTranslateY(0);
		window.getCenter().setTranslateX(0);
		window.getCenter().setStyle("-fx-background-color: snow; -fx-background-radius:3;");
		
		window.setTop(menu);
		window.getTop().setTranslateX(350);
		window.getTop().setTranslateY(45);
		
		HBox tmp_queue = new HBox();
		tmp_queue.setPrefSize(900.0, 65.0);
		window.setBottom(tmp_queue);
		window.getBottom().setTranslateX(-30);
		window.getBottom().setTranslateY(-40);
		
		window.setPrefHeight(heightScene);
		window.setPrefWidth(widthScene);	
	}
	
	public BorderPane getMenu() {
		return window;
	}
	
	public void setQueue(HBox queue) {
		window.setBottom(queue);
		window.getBottom().setTranslateX(-30);
		window.getBottom().setTranslateY(-40);

	}
	
	
}
