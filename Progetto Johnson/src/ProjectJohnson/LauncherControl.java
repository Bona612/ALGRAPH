package ProjectJohnson;

import java.io.File;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class LauncherControl
{
	private Pane menuButton;
	private UtilityButton ub;
	private GraphicControl gc;
	
	public LauncherControl() 
	{
		this.menuButton = new HBox();
		this.ub = new UtilityButton();
		this.menuButton.getChildren().add(setStartMenu());
		this.gc = new GraphicControl(this);
	}
	
	private HBox setStartMenu()
	{
		HBox startmenu = new HBox();
		startmenu.setId("Menu");
		startmenu.setPrefHeight(90);
	    startmenu.setSpacing(5);
		VBox root = new VBox();
	
	    GridPane menuPane = new GridPane();
	    menuPane.setGridLinesVisible(false);
	    menuPane.setHgap(5);
	
	    root.setAlignment(Pos.CENTER);
	    root.getChildren().add(menuPane);
	
	    
		ub.getCaricaGrafo().setOnMouseEntered(e -> {
			ub.getCaricaGrafo().setCursor(Cursor.HAND);
			});
		ub.getGeneraGrafo().setOnMouseEntered(e -> {
			ub.getGeneraGrafo().setCursor(Cursor.HAND);
			});
		ub.getSalvaGrafo().setOnMouseEntered(e -> {
			ub.getSalvaGrafo().setCursor(Cursor.HAND);
			});
		
	
		ub.getCaricaGrafo().setOnAction(e -> {
			 if (gc.getAlgorithm() != null && gc.getAlgorithm().getIsSet() == true)
			 {
				 gc.getAlgorithm().interruptAlgorithm();
			 }
			 
			 FileChooser fileC = new FileChooser();
			 fileC.getExtensionFilters().addAll(
					 new FileChooser.ExtensionFilter("ALGR file ","*." + "algr"),
					 new FileChooser.ExtensionFilter("Tutti i file", "*")
			 );
			 File file = fileC.showOpenDialog(null);
			
			 if (file == null)
			 {
				 return;
			 } 
	
			 try
			 {	
				 gc.setGraph(FileInOut.loadGraph(file));	
			 }
			 catch (IOException e1)
			 {
				 UtilityWindow.errorWindow("Errore", "Carica grafo", "Il file non è valido");     
			 }
		});
			    
				
		ub.getGeneraGrafo().setOnAction(e -> {
			 generateGraph();
		});
		
		ub.getSalvaGrafo().setOnAction(event -> {
		     try
		     {
		    	 if(gc.getAlgorithm() != null && gc.getAlgorithm().getIsSet() == true)
		    	 {
		 	  		gc.getAlgorithm().interruptAlgorithm();
		    	 }
		         FileChooser fc = new FileChooser();
		         fc.getExtensionFilters().addAll(
		        		 new FileChooser.ExtensionFilter("ALGR file ","*." + "algr"),
		        		 new FileChooser.ExtensionFilter("Tutti i file", "*")
		         );
		         File file = fc.showSaveDialog(null);
		         if (file == null)
		         {
		        	 return;
		         }
	
		         FileInOut.saveGraph(gc.getGraph(), file);
		            
		     }
		     catch (IOException e)
		     {
		    	 UtilityWindow.errorWindow("Errore","Salva grafo","Il grafo non è generato");
		     }
		});
	
		menuPane.add(ub.getCaricaGrafo(),1,0);
		menuPane.add(ub.getGeneraGrafo(), 2, 0);
		menuPane.add(ub.getSalvaGrafo(), 3, 0);
		menuPane.getStylesheets().addAll(this.getClass().getResource("../CssStyle/button.css").toExternalForm(),
					this.getClass().getResource("../CssStyle/buttonHover.css").toExternalForm());
		startmenu.setAlignment(Pos.BASELINE_CENTER);
		startmenu.getChildren().addAll(root);
		startmenu.setMaxSize(460.0, 27.0);
		
		return startmenu;
	}
	
	private void generateGraph()
	{  
		if (gc.getAlgorithm() != null && gc.getAlgorithm().getIsSet() == true)
		{
			gc.getAlgorithm().interruptAlgorithm();
		} 	
		try
		{
			GraphGenerator.GraphGeneratorDialogResult result =
			GraphGenerator.showGraphGeneratorDialog();
	
			if (result == null)
			{
				return;
	        }
	         
			Graph graph = new Graph();
	          
			graph = GraphGenerator.generateGraph(
					result.getNumNodes(),
					result.getMinWeight(),
					result.getMaxWeight()       
			);
	         
			gc.setGraph(graph);
		}
		catch (Exception e)
		{
			UtilityWindow.errorWindow("Errore","Genera grafo","Impossibile generare il grafo");
	    }
	}
 
	public Pane getMenuButton()
	{
		return menuButton;
	}

	public Scene getScene()
	{
		return  gc.getScene();
	}  
}
