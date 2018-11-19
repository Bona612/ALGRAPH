package ProjectJohnson;

import java.util.Optional;
import java.util.Random;


import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GraphGenerator
{
	 public static final int DEFAULT_NUM_NODES = 5 ;
	 public static final int DEFAULT_MIN_WEIGHT = 1 ;
	 public static final int DEFAULT_MAX_WEIGHT = 20 ;

	 private static final int MIN_NODES = 1 ;
	 private static final int MAX_NODES = 15 ;

	 public static Graph generateGraph() throws Exception
	 {
		 return generateGraph(DEFAULT_NUM_NODES) ;
	 }
	   
	 public static Graph generateGraph(int numNodes) throws Exception
	 {
		 return generateGraph(numNodes, DEFAULT_MIN_WEIGHT, DEFAULT_MAX_WEIGHT) ;
	 }
	 
	 public static Graph generateGraph(int numNodes, int minWeight, int maxWeight) throws Exception
	 {
		 if (numNodes > MAX_NODES || numNodes < MIN_NODES) 
		 {
			 throw new Exception() ;
	     }
		 if (maxWeight < minWeight || maxWeight < 0 || minWeight < 0)
		 {
			 throw new Exception() ;
	     }

		 Random random = new Random() ;
		 
		 int maxEdges = 0;
		 for(int i = 1; i < numNodes; i++)
		 {
			 maxEdges += i ;
		 }
		 maxEdges = maxEdges * 2 ;
	     int numEdges = random.nextInt(maxEdges + 1) ;

	     Graph graph = new Graph() ;

	     for(int i = 0; i < numNodes; i++)
	     {
	    	 graph.addNode(String.valueOf((char) (i + 65)));
	     }

	     int i = 0 ;

	     while(i < numEdges)
	     {
	    	 int rand1 = 0 ;
	    	 int rand2 = 0 ;

	         while(rand1 == rand2) 
	         {
	        	 rand1 = random.nextInt(numNodes) ;
	             rand2 = random.nextInt(numNodes) ;
	         }

	         Node[] nodes = graph.getGraph().keySet().toArray(new Node[0]) ;
	         Node n1 = nodes[rand1] ;
	         Node n2 = nodes[rand2] ;

	         int weight = random.nextInt(maxWeight - minWeight + 1) + minWeight ;
	         Edge newEdge = new Edge(n1, n2, DEFAULT_MIN_WEIGHT);
	         newEdge.setWeight(weight);
	        
	         if(graph.addEdge(newEdge) != null)
	         {
	        	 i++ ;
	         }
	     }
	     return graph ;
    }	
	 
	 /*
	 public void generateGraphGraphic() {
	        try {
	            GraphGeneratorDialogResult result =
	                    showGraphGeneratorDialog();

	            if (result == null) {
	                return;
	            }
	         
	           generateGraph(
	                    result.getNumNodes(),
	                    result.getMinWeight(),
	                    result.getMaxWeight()	               
	            );

	        } catch (Exception e) {
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Errore");
		        alert.setHeaderText("Genera grafo");
		        alert.setContentText("Impossibile generare il grafo");

		        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		        stage.show();
	        }
	   }
	*/ 
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

