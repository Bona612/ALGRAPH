package model;

public class test {

	public static void main(String[] args)
	{
		Node n1 = new Node("ciao") ;
		Node n2 = new Node("maio") ;
		Node n3 = new Node("bau") ;
		Node n4 = new Node("niao") ;
		Node n5 = new Node("rau") ;
		/*
		//System.out.println(n1.toString()) ;
		//System.out.println(n2.toString()) ;
		Edge e1 = new Edge(n1, n2) ;
		//System.out.println(e1.toString()) ;
		e1.setWeight(20) ;
		//System.out.println(e1.toString()) ;
		
		Node n3 = new Node("bau") ;
		
		Graph g = new Graph() ;
		g.addNode(n1) ;
		g.addNode(n2) ;
		g.addNode(n3) ;
		g.addEdge(e1) ;
		Edge e2 = new Edge(n2, n1, 10) ;
		Edge e3 = new Edge(n1, n3, 5) ;
		g.addEdge(e2) ;
		g.addEdge(e3) ;
		Set<Edge> se = g.getEdges() ;
		Set<Node> sn = g.getNodes() ;
		
		for(Node n : sn)
		{
			System.out.println(n.toString()) ;
		}
		for(Edge ed : se)
		{
			System.out.println(ed.toString()) ;
		}*/
		
		/*PriorityItem<Node> pi1 = new PriorityItem<Node>(n1, 14, 0) ;
		PriorityItem<Node> pi2 = new PriorityItem(2, 7, 1) ;
		PriorityItem<Node> pi3 = new PriorityItem(3, 8, 2) ;
		PriorityItem<Node> pi4 = new PriorityItem(4, 16, 3) ;
		PriorityItem<Node> pi5 = new PriorityItem(5, 10, 4) ;*/
		
		PriorityQueue<Object> pq = new PriorityQueue<Object>(5) ;
		System.out.println(pq.insert(n1, 14).getItem()) ;
		System.out.println(pq.insert(n2, 7).getItem()) ;
		System.out.println(pq.insert(n3, 8).getItem()) ;
		System.out.println(pq.insert(n4, 16).getItem()) ;
		System.out.println(pq.insert(n5, 10).getItem()) ;
		System.out.println(pq.toString()) ;
		System.out.println(pq.deleteMax().getPriority()) ;
		System.out.println(pq.toString()) ;
		System.out.println(pq.deleteMax().getPriority()) ;
		System.out.println(pq.toString()) ;
	}

}
