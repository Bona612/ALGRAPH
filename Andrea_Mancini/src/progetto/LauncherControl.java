package progetto;

import java.io.File;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class LauncherControl {
	
	private Pane menuButton;

//	private UtilityButton ub;
	private GraphicControl gc;
	
	public LauncherControl() {
		this.menuButton = new HBox();
		menuButton.getChildren().add(setStartMenu());
		 gc = new GraphicControl(this);			
	}
	
	private HBox setStartMenu() {

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
    vbox.setAlignment(Pos.CENTER);
    vbox.setStyle("-fx-padding: 5 0 0 0");

    menuPane.add(vbox, 1, 1, 3, 1);

    root.setAlignment(Pos.CENTER);
    root.getStyleClass().add("menuSection");
    root.getChildren().add(menuPane);

   
	Button b1 = new Button(), b2 = new Button(), b3 = new Button();
	
	b1.setText("Carica Grafo");
	b1.setContentDisplay(ContentDisplay.TOP);
	Image image1 = new Image(getClass().getResourceAsStream("Folder-Open-icon.png"));
	b1.setGraphic(new ImageView(image1));
	
	b2.setText("Genera Grafo");
	b2.setContentDisplay(ContentDisplay.TOP);
	Image image2 = new Image(getClass().getResourceAsStream("Add-File-icon.png"));
	b2.setGraphic(new ImageView(image2));
	
	b3.setText("Salva Grafo");
	b3.setContentDisplay(ContentDisplay.TOP);
	Image image3 = new Image(getClass().getResourceAsStream("Save-icon.png"));
	b3.setGraphic(new ImageView(image3));
	
	b1.setOnMouseEntered(e->{
		b1.setCursor(Cursor.HAND);
		});
	b2.setOnMouseEntered(e->{
		b2.setCursor(Cursor.HAND);
		});
	b3.setOnMouseEntered(e->{
		b2.setCursor(Cursor.HAND);
		});
	

	b1.setMaxSize(150.0, 25.0);
	b2.setMaxSize(150.0, 25.0);
	b3.setMaxSize(150.0, 25.0);

	b1.setOnAction( e->{

		FileChooser fileC= new FileChooser();
		fileC.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("ALGR file ","*." + "algr"),
	                new FileChooser.ExtensionFilter("Tutti i file", "*")
	        );
		File file = fileC.showOpenDialog(null);
		
		if (file == null) {
			return;
		} 

		try {

				gc.setGraph(FileInOut.loadGraph(file));
				
			} catch (IOException e1) {
				
				UtilityWindow.errorWindow("Errore", "Carica grafo", "Il file non è valido");
		         
			}
		});
		    
			
	b2.setOnAction( e->{

			generateGraph();

	}
					);
	
	b3.setOnAction(event->{
		
	     try {
	            
	            FileChooser fc = new FileChooser();
	            fc.getExtensionFilters().addAll(
		                new FileChooser.ExtensionFilter("ALGR file ","*." + "algr"),
		                new FileChooser.ExtensionFilter("Tutti i file", "*")
		        );
	            File file = fc.showSaveDialog(null);
	            if (file == null) {
	                return;
	            }

	            FileInOut.saveGraph(gc.getGraph(), file);
	            
	        } catch (IOException e) {

		         UtilityWindow.errorWindow("Errore","Salva grafo","Il grafo non è generato");
	        }
	});

	menuPane.add(b1,1,0);
	menuPane.add(b2, 2, 0);
	menuPane.add(b3, 3, 0);
	startmenu.setPadding(new Insets(100,100,100,100));
	startmenu.setAlignment(Pos.BASELINE_CENTER);
	startmenu.getChildren().addAll(root);
	return startmenu;

}

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

            gc.setGraph(graph);

        } catch (Exception e) {

	         UtilityWindow.errorWindow("Errore","Genera grafo","Impossibile generare il grafo");
        }
 
   }

  public Pane getMenuButton() {
	  return menuButton;
  }

  public Scene getScene() {

	  return  gc.getScene();
  }

  
}