package progetto;

import java.util.Optional;

import java.util.Scanner;


import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphicControl {

	private StartWindow sw;
	private Algorithm algorithm;
	private UtilityButton ub;
	private Graph graph;
	private Scene scene;
	private Node node;
	private Pane root = new Pane();
	private Stage stage;
	private double heightScene = 800.0, widthScene = 1200.0;
	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;

	
	public GraphicControl() {
		
	}
	
	public void setGraph(Graph graph) 
	{
		 this.graph = graph;
	     if (graph == null) 
	     {
	    	 return;
	     }
	     setGraphic();
	     setScene();
	     showGraph();
	}
	
	public void setGraphic()
	{
	       	node = new Node();
	        int k = 0;
	        double startX =	widthScene / 3;
	        double startY = heightScene / 3;
	        double radiusTop = Math.min(startX, startY) - node.getRadiusBack() ;
	        int n = graph.getNodes().size();

	        for (Node node : graph.getNodes()) {
	        	
	        	node.getNodeSP().setTranslateX(startX + radiusTop * Math.cos(k * 2 * Math.PI / n - Math.PI / 2));
	            node.getNodeSP().setTranslateY(startY + radiusTop * Math.sin(k * 2 * Math.PI / n - Math.PI / 2));
	        	
	            k++;
	        }

	       
	        updateGraphic();
	        
	  }

	public void updateGraphic() 
	{
		root.getChildren().clear();

        root.getChildren().addAll(graph.getEdges().stream().map(Edge::getArrow).collect(Collectors.toSet()));
        root.getChildren().addAll(graph.getNodes().stream().map(Node::getNodeSP).collect(Collectors.toSet()));
        setNodeControl();
        setEdgeControl();
	}
	
	private void setNodeControl() {
		
		for (Node node : graph.getNodes()) {
		node.getNodeSP().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e1) {
				orgSceneX = e1.getSceneX();
				orgSceneY = e1.getSceneY();
				orgTranslateX = ((StackPane)(e1.getSource())).getTranslateX();
				orgTranslateY = ((StackPane)(e1.getSource())).getTranslateY();
				
				if(e1.isSecondaryButtonDown()==true) 
					node.getPopup().show(node.getNodeSP(), e1.getScreenX(), e1.getScreenY());
				
			}
		});
		
		node.getNodeSP().setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e2) {
				
				if (e2.isPrimaryButtonDown() == true) {
					
				double offsetX = e2.getSceneX() - orgSceneX;
	            double offsetY = e2.getSceneY() - orgSceneY;
	            double newTranslateX =  orgTranslateX + offsetX;
	            double newTranslateY = orgTranslateY + offsetY;
	            
	            if ((newTranslateX >= (0.0)) && (newTranslateY <= (heightScene - 56.0)) && 
	            		((newTranslateX <= widthScene-56.0) && (newTranslateY >= 0.0))){
	            	             
		            ((StackPane)(e2.getSource())).setTranslateX(newTranslateX);
		            ((StackPane)(e2.getSource())).setTranslateY(newTranslateY);
		               
				}            
			  }
				
			}
		});
		
		node.getPopup().getItems().get(0).setOnAction( new EventHandler <ActionEvent>() {
		
		
			@Override
			public void handle(ActionEvent eventPopup) {
				
				TextInputDialog dialog = new TextInputDialog("Label");
		        dialog.setTitle("Aggiungi Nodo");
	            dialog.setHeaderText(null);
	            dialog.setContentText("Label:");

	            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
	            Optional<String> result = dialog.showAndWait();
	        	
	            if (result.isPresent()) {
	            	try {
	            		
	            		Node tmpNode = new Node(result.get());
	            		tmpNode.getNodeSP().setTranslateX(node.getNodeSP().getTranslateX());
	            		tmpNode.getNodeSP().setTranslateY(node.getNodeSP().getTranslateY());
	            		graph.addNode(tmpNode);
	            		
	            	}
	            	catch (Exception e) {
	            		Alert alert = new Alert(Alert.AlertType.ERROR);
	    		        alert.setTitle("Errore");
	    		        alert.setHeaderText("Aggiungi Nodo");
	    		        alert.setContentText("Impossibile aggiungere nodo");

	    		        Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
	    		        stageAlert.show();
	            	}
		        	
		        	updateGraphic();
	            }
			}
			
        });	
			
		 node.getPopup().getItems().get(1).setOnAction( new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				TextInputDialog dialog = new TextInputDialog("Edge");
	            dialog.setTitle("Aggiungi Arco");
	            dialog.setHeaderText(null);
	            dialog.setContentText("Nodo puntato:");

	            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
	            Optional<String> result = dialog.showAndWait();
	        	
	            if (result.isPresent()) {
	            	try {
	            		
	            		graph.addEdge(node.getLabel(), result.get());
	            		
	            	}
	            	catch (Exception e) {
	            		Alert alert = new Alert(Alert.AlertType.ERROR);
	    		        alert.setTitle("Errore");
	    		        alert.setHeaderText("Aggiungi Arco");
	    		        alert.setContentText("Impossibile aggiungere arco");

	    		        Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
	    		        stageAlert.show();
	            	}
		        	
		        	updateGraphic();
		            }
					
				}
				
			
			 
		 });
		 
		 node.getPopup().getItems().get(2).setOnAction( new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				graph.removeNode(node);
				updateGraphic();
			}
			 
		 });
		}

	}
	
	private void setEdgeControl() {
		
		
		for (Edge edge : graph.getEdges()) {
			
			edge.getArrow().setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					
					if(event.isSecondaryButtonDown()) {
						edge.getPopup().show(edge.getArrow(), event.getScreenX(), event.getScreenY());
					}
				}
				
				
			});
			
			
			edge.getPopup().getItems().get(0).setOnAction( new EventHandler<ActionEvent> (){
				
				
						@Override
						public void handle(ActionEvent event) {
							
						 TextInputDialog dialog = new TextInputDialog(String.valueOf(edge.getWeight()));
				         dialog.setTitle("Cambia Peso");
				         dialog.setHeaderText(null);
				         dialog.setContentText("Peso:");
		
				         Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		
				         Optional<String> result = dialog.showAndWait();
				         if (result.isPresent()) {
				        	edge.setWeight(Integer.parseInt(result.get()));
				         }
						 updateGraphic();
						 
					}
														
			});
			
			edge.getPopup().getItems().get(1).setOnAction(new EventHandler<ActionEvent> (){
				
				
				@Override
				public void handle(ActionEvent event) {
					
				    TextInputDialog dialog = new TextInputDialog(String.valueOf(edge.getN2().getLabel()));
		            dialog.setTitle("Modifica Arco");
		            dialog.setHeaderText(null);
		            dialog.setContentText("Nuovo nodo puntato:");

		            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		            Optional<String> result = dialog.showAndWait();
		        	
		            if (result.isPresent()) {
		            	try {
		            		Edge tmpEdge = new Edge(edge.getN1(), graph.getNode(String.valueOf(result.get())));
		            		tmpEdge.setWeight(edge.getWeight());
				        	graph.addEdge(tmpEdge);
				        	graph.removeEdge(edge);
		            	}
		            	catch (Exception e) {
		            		Alert alert = new Alert(Alert.AlertType.ERROR);
		    		        alert.setTitle("Errore");
		    		        alert.setHeaderText("Modifica Nodo");
		    		        alert.setContentText("Nodo non valido");

		    		        Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
		    		        stageAlert.show();
		            	}
			        	
			        	updateGraphic();
		            }
		         
		            else 
		            	graph.removeEdge(edge);
		            
				}	
				
											
			});
					
			edge.getPopup().getItems().get(2).setOnAction( event ->{
					graph.removeEdge(edge);
					updateGraphic();
									
			});
			
		}
		
	}
	
	private void setScene() {
		
		ub = new UtilityButton();
		sw = new StartWindow(root, ub.getBox());
		scene = new Scene(sw.getWindow());
		scene.widthProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ObservableValue, Number oldSceneWidth, Number newSceneWidth) {
				widthScene = (double)newSceneWidth;
				/*
				for(Node node :graph.getNodes()) {
				 if (node.getNodeSP().getTranslateX() <= (0.0)) {
					 node.getNodeSP().setTranslateX(28.0);
				 }
				
				 else if (node.getNodeSP().getTranslateX() >= widthScene-56.0) {
					node.getNodeSP().setTranslateX(widthScene-56.0);
						 
				 }
				
				}
				*/
			}
			
				
		});
		scene.heightProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ObservableValue, Number oldSceneHeight, Number newSceneHeight) {
				heightScene= (double)newSceneHeight;
				/*
				for(Node node :graph.getNodes()) {
				if  (node.getNodeSP().getTranslateY() >= (heightScene - 56.0)) {
					 
					node.getNodeSP().setTranslateY(heightScene-56.0);
					
					 
				 }
				
				else if (node.getNodeSP().getTranslateY() <= (0.0)) {
					
					node.getNodeSP().setTranslateY(0.0);
					
					}
				
				}
				*/
			}
		});
		
		
	}
	public void setAlgorithm() {
		
		TextInputDialog dialog = new TextInputDialog("Label");
        dialog.setTitle("Scegli Nodo");
        dialog.setHeaderText(null);
        dialog.setContentText("Label:");

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        Optional<String> result = dialog.showAndWait();
    	
        if (result.isPresent()) {
        	try {  		
        		
        		
        		algorithm = new Algorithm(graph);
        		algorithm.setStartNode(graph.getNode(result.get())) ;
        	}
        	catch (Exception e) {
        		Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Errore");
		        alert.setHeaderText("Aggiungi Nodo");
		        alert.setContentText("Impossibile aggiungere nodo");

		        Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
		        stageAlert.show();
        	}
        	
        }
	
		ub.getEsegui().setOnAction(event->{
			algorithm.executeAll();
			updateGraphic();
		});
		
		ub.getPasso().setOnAction(event->{
			algorithm.executeStep();
			updateGraphic();
		});
	}
	public Scene getScene() {
		
		return this.scene;

	}
	
	public Pane getPane() {
		return root;
	}
	
	public Graph getGraph() {
		return graph;
	}

	
	private void showGraph() {
		
		this.stage = new Stage();
		stage.setScene(scene);
		stage.setHeight(heightScene);
		stage.setWidth(widthScene);
		stage.show();
		setAlgorithm();
	}
	
	
}

