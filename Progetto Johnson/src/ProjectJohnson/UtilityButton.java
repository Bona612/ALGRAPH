package ProjectJohnson;

import javafx.event.ActionEvent;


import javafx.event.EventHandler;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UtilityButton {
	
	private Button caricaGrafo;
	private Button generaGrafo;
	private Button salvaGrafo;
	private Button Passo;
	private Button Esegui;
	private Button Restart;
	private Button Pause;
	private VBox box;
	boolean disable = false;

	
	public UtilityButton () {
		
		setCaricaGrafo();
		setGeneraGrafo();
		setSalvaGrafo();
		setEsegui();
		setPasso();
		setRestart();
		setPause();
		setBox();
	}
		
	public Button getCaricaGrafo() {
		return caricaGrafo;
	}


	public void setCaricaGrafo() {
		caricaGrafo = new Button("Carica Grafo");
		caricaGrafo.setContentDisplay(ContentDisplay.TOP);
		caricaGrafo.getStyleClass().addAll("../CssStyle/button.css", "../CssStyle/buttonHover.css");
		Image image1 = new Image(getClass().getResourceAsStream("../icon/Folder-Open-icon.png"));
		caricaGrafo.setGraphic(new ImageView(image1));
		caricaGrafo.setMaxSize(150.0, 25.0);
		
	}


	public Button getGeneraGrafo() {
		return generaGrafo;
	}


	public void setGeneraGrafo() {

		generaGrafo = new Button("Genera Grafo");
		generaGrafo.setContentDisplay(ContentDisplay.TOP);
		generaGrafo.getStyleClass().addAll("../CssStyle/button.css", "../CssStyle/buttonHover.css");
		Image image2 = new Image(getClass().getResourceAsStream("../icon/Add-File-icon.png"));
		generaGrafo.setGraphic(new ImageView(image2));
		generaGrafo.setMaxSize(150.0, 25.0);
		
		
	}


	public Button getSalvaGrafo() {
		return salvaGrafo;
	}


	public void setSalvaGrafo() {
		
		salvaGrafo = new Button("Salva Grafo");
		salvaGrafo.setContentDisplay(ContentDisplay.TOP);
		salvaGrafo.getStyleClass().addAll("../CssStyle/button.css", "../CssStyle/buttonHover.css");
		Image image3 = new Image(getClass().getResourceAsStream("../icon/Save-icon.png"));
		salvaGrafo.setGraphic(new ImageView(image3));
		salvaGrafo.setMaxSize(150.0, 25.0);
		
	}
	public Button getRestart() {
		return Restart;
	}


	public void setRestart() {
		Restart = new Button("Restart");
		Restart.setContentDisplay(ContentDisplay.TOP);
		Restart.getStyleClass().addAll("../CssStyle/button.css", "../CssStyle/buttonHover.css");
		Restart.setDisable(true);
		Image image = new Image(getClass().getResourceAsStream("../icon/Reload-icon.png"));
		Restart.setGraphic(new ImageView(image));
		Restart.getStyleClass().add("../CssStyle/button.css");
		Restart.setMaxSize(48, 48);
		
	}


	public Button getPause() {
		return Pause;
	}


	public void setPause() {
		Pause = new Button("Pausa");
		Pause.setContentDisplay(ContentDisplay.TOP);
		Pause.getStyleClass().addAll("../CssStyle/button.css", "../CssStyle/buttonHover.css");
		Pause.setDisable(true);
		Image image = new Image(getClass().getResourceAsStream("../icon/pause-icon.png"));
		Pause.setGraphic(new ImageView(image));
		Pause.setMaxSize(48, 48);
		
	}


	public Button getPasso() {
		return Passo;
	}
	public void setPasso() {
		
		Passo = new Button("Passo");
		Passo.setContentDisplay(ContentDisplay.TOP);
		Passo.setDisable(true);
		Passo.setId("button");
		Passo.getStyleClass().addAll("../CssStyle/button.css", "../CssStyle/buttonHover.css");
		Image image = new Image(getClass().getResourceAsStream("../icon/Last-icon.png"));
		Passo.setGraphic(new ImageView(image));
		Passo.setMaxSize(48, 48);
	
	}
	public Button getEsegui() {
		return Esegui;
	}
	public void setEsegui() {
		Esegui = new Button("Esegui");
		Esegui.setContentDisplay(ContentDisplay.TOP);
		Esegui.getStyleClass().addAll("../CssStyle/button.css", "../CssStyle/buttonHover.css");
		Esegui.setDisable(true);
		Image image = new Image(getClass().getResourceAsStream("../icon/play-icon.png"));
		Esegui.setGraphic(new ImageView(image));
	}
	
	public VBox getBox() {
		return box;
	}

	public void setBox() {
		box = new VBox();
		box.getChildren().addAll(Passo, Esegui, Pause, Restart);
		box.setAlignment(Pos.CENTER);
		box.setStyle("-fx-spacing: 5;");
		box.getStylesheets().addAll(this.getClass().getResource("../CssStyle/button.css").toExternalForm(),
									this.getClass().getResource("../CssStyle/buttonHover.css").toExternalForm());
		box.setMaxSize(48.0, 144.0);
	}
	
	
}
