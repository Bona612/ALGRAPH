package progetto;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class UtilityButton {
	

	private Button Passo;
	private Button Esegui;
	private VBox box;

	public UtilityButton () {

		setEsegui();
		setPasso();
		setBox();
	}
		
	 
	public Button getPasso() {
		return Passo;
	}
	public void setPasso() {
		
		Passo = new Button("Passo");
		Passo.setContentDisplay(ContentDisplay.TOP);
		Passo.setStyle("-fx-background-color: transparent; ");
		//Passo.getStyleClass().add(".button:pressed { -fx-scale-y: 0.9;};");
	//	System.out.println(Passo.getStyle());
		Image image = new Image(getClass().getResourceAsStream("Last-icon.png"));
		Passo.setGraphic(new ImageView(image));
		Passo.setMaxSize(48, 48);
	
	}
	public Button getEsegui() {
		return Esegui;
	}
	public void setEsegui() {
		Esegui = new Button("Esegui");
		Esegui.setContentDisplay(ContentDisplay.TOP);
		Esegui.setStyle("-fx-background-color: transparent; ");
	
		Image image = new Image(getClass().getResourceAsStream("play-icon.png"));
		Esegui.setGraphic(new ImageView(image));
	}

	public VBox getBox() {
		return box;
	}

	public void setBox() {
		box = new VBox();
		box.getChildren().addAll(Passo, Esegui);
		box.setAlignment(Pos.CENTER);
		box.setStyle("-fx-spacing: 5;");
		box.setMaxSize(30, 60);
	}
	
	
}

