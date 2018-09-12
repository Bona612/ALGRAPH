package progetto;

/*
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
*/

public class Prova2 //extends Application
{
	public static void main(String[] args) 
	{
		//launch(args);
		Graph g = new Graph();
		try {
			g.setGraph(GraphGenerator.generateGraph().getGraph()) ;
			Algorithm alg = new Algorithm(g) ;
			alg.setStartNode(g.getNode("A")) ;
			alg.executeAll() ;
		}
		catch(Exception e) {
			System.out.println(e.getClass()) ;
		}
		
	}
	/*
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Node n1 = new Node("ciao") ;
		Node n2 = new Node("maio") ;
		Node n3 = new Node("bau") ;
		Node n4 = new Node("niao") ;
		Node n5 = new Node("rau") ;
		PriorityQueue<Object> pq = new PriorityQueue<Object>(5) ;
		
		System.out.println(pq.insert(n1, 14).getItem()) ;
		System.out.println(pq.insert(n2, 7).getItem()) ;
		System.out.println(pq.insert(n3, 8).getItem()) ;
		System.out.println(pq.insert(n4, 16).getItem()) ;
		System.out.println(pq.insert(n5, 10).getItem()) ;
		System.out.println(pq.toString()) ;
		
		ItemGraphic<Node> ig = new ItemGraphic<Node>((PriorityItem)pq.getMax()) ;
		Pane pane = new Pane() ;
		pane.getChildren().add(ig) ;
		Scene s = new Scene(pane) ;
		primaryStage.setScene(s);
		primaryStage.sizeToScene();
		primaryStage.show();
	
	}
	*/
}
