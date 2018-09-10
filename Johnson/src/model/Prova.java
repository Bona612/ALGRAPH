package model;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.FileHandler;

import algorithm.FileInOut;
import algorithm.GraphGenerator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Prova extends Application {

	public static void main(String[] args) {
		
		launch(args);

	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
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
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	};
	
	  private void generateGraph() {
	        try {
	            GraphGeneratorDialogResult result =
	                    showGraphGeneratorDialog();

	            if (result == null) {
	                return;
	            }
	            Graph graph = new Graph();
	            
	            graph = GraphGenerator.generateGraph(
	                    result.getNumNodes(),
	                    result.getMinWeight(),
	                    result.getMaxWeight()
	               
	            );
	            
	          //  GraphicControl.setGraph(graph);

	            

	        } catch (Exception e) {
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Errore");
		        alert.setHeaderText("Genera grafo");
		        alert.setContentText("Impossibile generare il grafo");

		        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		        stage.showAndWait();
	        }
	   }

	  public static GraphGeneratorDialogResult showGraphGeneratorDialog() throws Exception{
		
		Dialog<GraphGeneratorDialogResult> dialog = new Dialog<>();
        dialog.setTitle("Genera nuovo grafo");

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();

        ButtonType generateButtonType = new ButtonType("genera", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(generateButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField numNodes = new TextField();
        numNodes.setText(String.valueOf(GraphGenerator.DEFAULT_NUM_NODES));
        TextField minWeight = new TextField();
        minWeight.setText(String.valueOf(GraphGenerator.DEFAULT_MIN_WEIGHT));
        TextField maxWeight = new TextField();
        maxWeight.setText(String.valueOf(GraphGenerator.DEFAULT_MAX_WEIGHT));
      

        grid.add(new Label("Numero nodi:"), 0,0);
        grid.add(numNodes, 1, 0);
        grid.add(new Label("Peso minimo:"), 0,1);
        grid.add(minWeight, 1, 1);
        grid.add(new Label("Peso massimo:"), 0,2);
        grid.add(maxWeight, 1, 2);
      

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == generateButtonType) {
                try {
                    return new GraphGeneratorDialogResult(
                            Integer.parseInt(numNodes.getText()),
                            Integer.parseInt(minWeight.getText()),
                            Integer.parseInt(maxWeight.getText())
                    );
                } catch (NumberFormatException e) {
                    return new GraphGeneratorDialogResult(e);
                }
            }

            return null;
        });

        Optional<GraphGeneratorDialogResult> result = dialog.showAndWait();
        if (result.isPresent()) {
            GraphGeneratorDialogResult res = result.get();
            if (res.getException() != null) {
                throw res.getException();
            }
            return res;
        }
        

        return null;
    }
	 public static class GraphGeneratorDialogResult {
	       
	        private int numNodes;
	        private int minWeight;
	        private int maxWeight;

	        private Exception exception;

	        /**
	         * Initializes result with an error
	         * @param exception Exception threw by dialog
	         */
	        public GraphGeneratorDialogResult(Exception exception) {
	            this.exception = exception;
	        }

	        /**
	         * Initializes result with a correct result
	         * @param numNodes Number of nodes to generate
	         * @param minWeight Minimum weight of graph edges
	         * @param maxWeight Maximum weight of graph edges
	         * @param directed Graph directed
	         */
	        public GraphGeneratorDialogResult(int numNodes, int minWeight, int maxWeight) {
	            this.numNodes = numNodes;
	            this.minWeight = minWeight;
	            this.maxWeight = maxWeight;
	           
	        }
	        public int getNumNodes() {
	            return numNodes;
	        }

	        public int getMinWeight() {
	            return minWeight;
	        }

	        public int getMaxWeight() {
	            return maxWeight;
	        }

	        public Exception getException() {
	            return exception;
	        }
	    }
	 
	 
}
