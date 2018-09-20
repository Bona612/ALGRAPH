package progetto;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UtilityButton 
{
	private Button Passo ;
	private Button Esegui ;
	private VBox box ;

	public UtilityButton()
	{
		setEsegui() ;
		setPasso() ;
		setBox() ;
	}
			 
	public Button getPasso() 
	{
		return Passo ;
	}
	public void setPasso()
	{	
		Passo = new Button("Passo") ;
	}
	
	public Button getEsegui()
	{
		return Esegui ;
	}
	
	public void setEsegui() 
	{
		Esegui = new Button("Esegui") ;
	}

	public VBox getBox() 
	{
		return box ;
	}

	public void setBox() 
	{
		box = new VBox() ;
		box.getChildren().addAll(Passo, Esegui) ;
		box.setAlignment(Pos.CENTER_RIGHT) ;
		box.autosize() ;
	}
}
