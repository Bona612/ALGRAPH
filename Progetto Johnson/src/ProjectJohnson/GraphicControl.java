package ProjectJohnson;

import java.util.Optional;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GraphicControl
{	
	private ContextMenu ContextMenu;
	private Algorithm algorithm;
	protected UtilityButton ub;
	private StartWindow startWindow;
	protected Graph graph;
	private Pane menuButton;
	private Scene scene;
	private Node node;
	private Pane root;
	private double heightRoot = 550.0, widthRoot = 650.0;
	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	
	public GraphicControl(LauncherControl lc)
	{
		this.menuButton = lc.getMenuButton();
		this.algorithm = null ;
		setRoot();
		setScene();
	}
	
	public void setGraph(Graph graph)
	{
		 this.graph = graph;
	     if (graph == null)
	     {
	    	 return;
	     }
	     
	     ub.getEsegui().setDisable(false);
	     ub.getPasso().setDisable(false);
	     setGraphic();
	     //algorithm = new Algorithm(this);
	     setAlgorithm();   
	}
	
	public void setGraphic()
	{	
	       	node = new Node();
	        int k = 0;
	        double startX =	widthRoot / 2.2;
	        double startY = heightRoot / 2.2;
	        double radiusTop = Math.min(startX, startY) - node.getRadius();
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
	
	private void setNodeControl()
	{
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
	            
	            if ((newTranslateX >= (0.0)) && (newTranslateY <= (heightRoot - 56.0)) && 
	            		((newTranslateX <= widthRoot-56.0) && (newTranslateY >= 0.0))){
	            	             
		            ((StackPane)(e2.getSource())).setTranslateX(newTranslateX);
		            ((StackPane)(e2.getSource())).setTranslateY(newTranslateY);
		               
				}            
			  }
				
			}
		});
		
		
			
		 node.getPopup().getItems().get(0).setOnAction( new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Graph tmp_graph = new Graph();
				
				for (Node node : graph.getNodes()) {
					tmp_graph.addNode(node);
				}
				
				tmp_graph.removeNode(node);
		        Optional<String> result = UtilityWindow.choiceWindow("Aggiungi Arco","Nodo puntato:", tmp_graph);
	        	
	            if (result.isPresent()) {
	            	try {
	            		
	            		graph.addEdge(node.getLabel(), result.get());
	            		
	            	}
	            	catch (Exception e) {

	    		        UtilityWindow.errorWindow("Errore","Aggiungi Arco","Impossibile aggiungere arco");

	            	}
		        	
		        	updateGraphic();
		            }
					
				}
				
			
			 
		 });
		 
		 node.getPopup().getItems().get(1).setOnAction( new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				graph.removeNode(node);
				updateGraphic();
			}
			 
		 });
		}

	}
	
	private void setEdgeControl()
	{
		for (Edge edge : graph.getEdges()) 
		{	
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
							
							
		
				         Optional<String> result = UtilityWindow.inputWindow("Cambia Peso", String.valueOf(edge.getWeight()), "Peso:");
				         if (result.isPresent()) {
				        	 try {
				        	edge.setWeight(Integer.parseInt(result.get()));
				        	 } catch (Exception e) {
				        		 
				        		 UtilityWindow.errorWindow("Errore", "Cambia Peso", "Peso non valido");
				        	 }
				         }
						 updateGraphic();
						 
					}
														
			});
			
			edge.getPopup().getItems().get(1).setOnAction(new EventHandler<ActionEvent> (){
				

				
				@Override
				public void handle(ActionEvent event) {
					
					Graph tmp_graph = new Graph();
					
					for (Node node : graph.getNodes()) {
						tmp_graph.addNode(node);
					}
												
					tmp_graph.removeNode(edge.getN1());
					tmp_graph.removeNode(edge.getN2());
					
			        Optional<String> result = UtilityWindow.choiceWindow("Aggiungi Arco", "Nodo puntato:", tmp_graph);
		        	
		            if (result.isPresent()) {
		            	try {
		            		Edge tmpEdge = new Edge(edge.getN1(), graph.getNode(String.valueOf(result.get())));
		            		tmpEdge.setWeight(edge.getWeight());
				        	graph.addEdge(tmpEdge);
				        	graph.removeEdge(edge);
		            	}
		            	catch (Exception e) {

		    		        UtilityWindow.errorWindow("Errore","Modifica Nodo","Nodo non valido");

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
	
	private void setNodePosition(Node node) 
	{	
		if (node.getNodeSP().getTranslateX() > root.getWidth() - 56.0) {
            node.getNodeSP().setTranslateX(root.getWidth() - 56.0);
        }
        if (node.getNodeSP().getTranslateX() < root.getTranslateX() + 56.0) {
        	 node.getNodeSP().setTranslateX(root.getTranslateX() + 56.0);
        }
        if ( node.getNodeSP().getTranslateY() > root.getHeight() - 56.0) {
        	node.getNodeSP().setTranslateY(root.getHeight() - 56.0);
        }
        if (node.getNodeSP().getTranslateY() < root.getTranslateY() + 56.0) {
        	node.getNodeSP().setTranslateY(root.getTranslateY() + 56.0);
        }
	}
	
	private void setRoot()
	{
		root = new Pane();
		ContextMenu = new ContextMenu();
		root.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown())
					ContextMenu.hide();				
			}
			
		});
		root.setOnContextMenuRequested(event-> {
		ContextMenu.hide();
		 
		if (event.getPickResult().getIntersectedNode().getClass().equals(Pane.class)) {
			
            		ContextMenu ContextMenu = new ContextMenu(); 
            		ContextMenu.setStyle("-fx-selection-bar: black; -fx-border-color: black;" );
            		this.ContextMenu = ContextMenu; 
     	            MenuItem item1 = new MenuItem("Crea Nodo");
     	            ContextMenu.getItems().add(item1);  
     	            ContextMenu.show((Pane) event.getSource(), event.getScreenX(), event.getScreenY());
     	            item1.setOnAction(eventAction ->{
     	            	
    			    Optional<String> result = UtilityWindow.inputWindow("Aggiungi Nodo", "Label", "Label:");
    		        	
    		            if (result.isPresent()) {
    		            	try {
    		            		
    		            		 Node node = new Node(result.get());
    		 	            	 node.getNodeSP().setTranslateX(event.getX());
    		 	            	 node.getNodeSP().setTranslateY(event.getY());
    		 	            	 setNodePosition(node);
    		 	            	 
    		 	            	 if (graph == null) {
    		 	            		 graph = new Graph();
    		 	            		 graph.addNode(node);
    		 	            		 
    		 	            		 setGraph(graph);
    		 	            	 }
    		 	            	 else { 
    		 	            		 graph.addNode(node);
    		 	            		 algorithm.getQueue().incCapacity();
    		 	            	 }
    		            	}
    		            	catch (Exception e) {

    		    		        UtilityWindow.errorWindow("Errore","Aggiungi Nodo","Impossibile aggiungere il nodo");

    		            	}
    			        	
    			        	updateGraphic();
    		            }
	 	            	 
	 	            });
     	          	           
		}
		 
	  });

	}
	           
	private void setScene()
	{
		ub = new UtilityButton();
		root.setMaxWidth(widthRoot);
		root.setMaxHeight(heightRoot);
		this.startWindow = new StartWindow(root, ub.getBox(), menuButton);
		this.startWindow.getMenu().setId("pane");
		this.scene = new Scene(startWindow.getMenu());
	}
	
	public void setAlgorithm()
	{
		if(this.algorithm == null)
		{
			this.algorithm = new Algorithm(this);
		}
		else
		{
			this.algorithm.setControlGraph();
			//this.algorithm.resetAlgorithm();
		}
		this.startWindow.setQueue(this.algorithm.getQueue().getGraphicQueue());
		ub.getEsegui().setOnAction(event->{
			if  (algorithm.getIsSet() == false)
			{	
				Optional<String> result = UtilityWindow.choiceWindow("Nodo di partenza", "Nodo di partenza:", graph);
		    	
		        if (result.isPresent())
		        {
		        	ub.getPause().setDisable(false);
		        	try 
		        	{
		        		algorithm.setStartNode(graph.getNode(result.get()));
		        	}
		        	catch (Exception e)
		        	{
				        UtilityWindow.errorWindow("Errore","Nodo di partenza","Impossibile settare il nodo");
		        	}
		        	
		        }
		        else 		        	
		        	return;
		        
			}
			algorithm.setAutomatic();
			algorithm.executeAll();
			updateGraphic();
		});
	
		ub.getPasso().setOnAction(event->{
			
			if  (algorithm.getIsSet() == false) {
				
				
				Optional<String> result = UtilityWindow.choiceWindow("Nodo di partenza", "Nodo di partenza:", graph);
		    	
		        if (result.isPresent()) {
		        	ub.getPause().setDisable(false);
		        	try {  		
		        		
		        		
		        		algorithm.setStartNode(graph.getNode(result.get()));
		        	}
		        	catch (Exception e) {
				        UtilityWindow.errorWindow("Errore","Nodo di partenza","Impossibile settare il nodo");

		        	}
		        	
		        	}
		        else 
		        	return;
		        
			}
			algorithm.executeAll();
			updateGraphic();
		});
		
		ub.getPause().setOnAction(event->{
			ub.getPause().setDisable(true);
			ub.getPasso().setDisable(false);
			ub.getRestart().setDisable(false);
			ub.getEsegui().setDisable(false);
			algorithm.setPause();
		});
		
		ub.getRestart().setOnAction(event->{
			algorithm.resetAlgorithm();
		});
		
	}

	public UtilityButton getUtilityButton() {
		return this.ub;
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
	
	public Algorithm getAlgorithm() {
		return this.algorithm;
	}
	
	public StartWindow getStartWindow() {
		return startWindow;
	}

	
}
