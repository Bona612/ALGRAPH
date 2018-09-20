package progetto;

import java.io.File;
import java.io.IOException;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LauncherControl {
	
	private Scene scene;
	private GraphicControl gc;
	
	public LauncherControl() {
		this.scene = setStartMenu();
	}
	
	private Scene setStartMenu() {
		
	HBox startmenu = new HBox();
	startmenu.setId("Menu");
	startmenu.setPrefHeight(90);
    startmenu.setSpacing(5);
	VBox root = new VBox();

    GridPane menuPane = new GridPane();
    menuPane.setGridLinesVisible(false);
    menuPane.setHgap(5);

   
    Label label = new Label("Grafo");

    VBox vbox = new VBox();
    vbox.getChildren().add(label);
    VBox.setVgrow(label, Priority.ALWAYS);
    vbox.setAlignment(Pos.BOTTOM_CENTER);
    vbox.setStyle("-fx-padding: 5 0 0 0");

    menuPane.add(vbox, 0, 1, 3, 1);

    root.setAlignment(Pos.CENTER);
    root.getStyleClass().add("menuSection");
    root.getChildren().add(menuPane);

   
	Button b1 = new Button(), b2 = new Button();
	
	b1.setText("Carica Grafo");
	b2.setText("Genera Grafo");
	b1.setOnMouseEntered(e->{
		b1.setCursor(Cursor.HAND);
		});
	b2.setOnMouseEntered(e->{
		b2.setCursor(Cursor.HAND);
		});
	

	b1.setMaxSize(150.0, 25.0);
	b2.setMaxSize(150.0, 25.0);
	b1.setOnAction( e->{
		
		FileChooser fileC= new FileChooser();
		File file = fileC.showOpenDialog(null);
		if (file == null) {
			 Alert alert = new Alert(Alert.AlertType.WARNING);
	         alert.setTitle("Errore");
	         alert.setHeaderText("Carica grafo");
	         alert.setContentText("Il file non è valido");

	         Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	         stage.showAndWait();
			
		} else {
			try {
				FileInOut.loadGraph(file);
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}
		    
	}
			);
	b2.setOnAction( e->{
			generateGraph();
	}
					);
	
	menuPane.add(b1,1,0);
	menuPane.add(b2, 2, 0);
	startmenu.setPadding(new Insets(100,100,100,100));
	startmenu.setAlignment(Pos.BASELINE_CENTER);
	startmenu.getChildren().addAll(root);
	Scene scene = new Scene(startmenu);
	
	return scene;
};

  private void generateGraph() {
        try {
            GraphGenerator.GraphGeneratorDialogResult result =
                    GraphGenerator.showGraphGeneratorDialog();

            if (result == null) {
                return;
            }
      
            Graph graph = new Graph();
          
            graph = GraphGenerator.generateGraph(
                    result.getNumNodes(),
                    result.getMinWeight(),
                    result.getMaxWeight()
               
            );

            GraphicControl gc = new GraphicControl();

            gc.setGraph(graph);


        } catch (Exception e) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Errore");
	        alert.setHeaderText("Genera grafo");
	        alert.setContentText("Impossibile generare il grafo");

	        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	        stage.showAndWait();
        }
   }

  public Pane getPane() {
	  return gc.getPane();
  }
  public Graph getGraph() {
	  return gc.getGraph();
  }
  public Scene getScene() {
	  return this.scene;
  }

  
}