package progetto;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StartWindow
{
	private BorderPane window ;
	private double heightScene = 800 ;
	private double widthScene = 1000 ;
	
	public BorderPane getWindow() 
	{
		return window ;
	}

	public StartWindow(Pane root, VBox buttons)
	{
		window = new BorderPane() ;
		window.setRight(buttons) ;
		window.setCenter(root) ;
		window.setPrefHeight(heightScene) ;
		window.setPrefWidth(widthScene) ;	
	}
}