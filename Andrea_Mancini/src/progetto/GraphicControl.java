package progetto;

import java.util.Scanner;


import java.util.stream.Collectors;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphicControl {

	private Graph graph;
	private Scene scene;
	private Node node;
	private Pane root;
	private Stage stage;
	private double heightScene = 800.0, widthScene = 1200.0;
	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;

	
	public GraphicControl() {
		setScene();
	}
	
	public void setGraph(Graph graph) {
		 this.graph = graph;
	        if (graph == null) {
	            return;
	       }
	        setGraphic();
	        showGraph();
	}
	public void setGraphic() {
	       	node = new Node();
	        int k = 0;
	        
	        double startX = widthScene / 3;
	        double startY = heightScene / 3;
	        //double radiusBack = Math.min(startX, startY) - node.getRadiusBack()- 10;
	        double radiusTop = Math.min(startX, startY) - node.getRadiusTop()- 10;
	        int n = graph.getNodes().size();

	        for (Node node : graph.getNodes())
	        {
	        	node.getNodeSP().setTranslateX(startX + radiusTop * Math.cos(k * 2 * Math.PI / n - Math.PI / 2));
	            node.getNodeSP().setTranslateY(startY + radiusTop * Math.sin(k * 2 * Math.PI / n - Math.PI / 2));
	        	
	            k++;
	        }

	       
	        updateGraphic();
	        
	        setNodeControl();
	  }

	public void updateGraphic() {
		root.getChildren().clear();

        root.getChildren().addAll(graph.getEdges().stream().map(Edge::getArrow).collect(Collectors.toSet()));
        root.getChildren().addAll(graph.getNodes().stream().map(Node::getNodeSP).collect(Collectors.toSet()));
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
		}) ;
		
	
		
		 node.getPopup().getContent().get(0).setOnMouseClicked( new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getSource().equals(node.popup.items.get(0))){
					
					Scanner input = new Scanner (System.in);
					String label = input.nextLine();
					Node node = new Node(label);
					graph.addNode(node);
				}
				else if (event.getSource().equals(node.popup.items.get(1))) {
					
					graph.removeNode(node);
				}
			}
			 
		 });
		}
		setEdgeControl();
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
			edge.getPopup().getContent().get(0).setOnMousePressed( new EventHandler <MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if(event.getSource().equals(node.popup.items.get(0))){	
						
						Scanner input = new Scanner(System.in);
						int weight = input.nextInt();
						edge.setWeight(weight);
					}
					else if (event.getSource().equals(node.popup.items.get(1))) {
						Node A = edge.getN1(), B = edge.getN2();
						graph.removeEdge(edge);
						graph.addEdge(B, A);
						
					}
					
				}
							
			});
		}
	}
	
	private void setScene() {
		root = new Pane();
		scene = new Scene(root);
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
	
	public Scene getScene() {
		
		return this.scene;

	}
	
	private void showGraph() {
		this.stage = new Stage();
		stage.setScene(scene);
		stage.setHeight(heightScene);
		stage.setWidth(widthScene);
		stage.show();
	}
	
	
}

