package progetto;

	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
	import java.util.Map;
	import java.util.Optional;
	import java.util.Set;
	import java.util.stream.Collectors;

	public class Graph
	{
		private Map<Node, Set<Edge>> graph ;
		
		public Graph()
		{
			this.graph = new HashMap<>() ;
		}
		
		public Map<Node, Set<Edge>> getGraph()
		{
	        return graph ;
	    }
		
		public void setGraph(Map<Node, Set<Edge>> graph)
		{
			this.graph = graph ;
		}
		
		public Set<Node> getNodes()
		{
	        return graph.keySet() ;
	    }
		
		public Set<Edge> getEdges()
		{
	        List<Set<Edge>> setList = new ArrayList<>(graph.values()) ;

	        if (setList.size() == 0)
	        {
	            return new HashSet<>() ;
	        }

	        return setList.stream().collect(() -> new HashSet<>(setList.get(0)), Set::addAll, Set::addAll) ;
	    }
		
		public Node getNode(String label)
		{
	        return getNode(new Node(label)) ;
	    }

	    public Node getNode(Node node)
	    {
	        Optional<Node> optionalNode = getNodes().stream().filter(n -> n.equals(node)).findAny() ;
	        return optionalNode.orElse(null) ;
	    }
		
	    public Node addNode(String label)
	    {
	        return addNode(new Node(label)) ;
	    }

	    public Node addNode(Node node)
	    {
	    	Node tmp ;
	    	Iterator<Node> ns = getNodes().iterator() ;
	    	while(ns.hasNext())
	    	{
	    		tmp = ns.next() ; 
	    		if(node.equals(tmp))
		        {
		            return null;
		        }
	    	}
	        graph.put(node, new HashSet<>()) ;

	        return node ;
	    }

	    public boolean removeNode(String label)
	    {
	        return this.removeNode(new Node(label)) ;
	    }
	    
	    public boolean removeNode(Node node)
	    {
	        if(!graph.containsKey(node))
	        {
	            return false ;
	        }
	        
	        graph.remove(node);
	        
	        for(Node n : graph.keySet())
	        {
	           graph.put(n, graph.get(n).stream().filter(e -> !e.getN2().equals(node)).collect(Collectors.toSet())) ;
	        }

	        return true ;
	    }
	    
	    public Edge addEdge(String label1, String label2)
	    {
	        Node node1 = getNode(label1) ;
	        Node node2 = getNode(label2) ;

	        if (node1 == null || node2 == null)
	        {
	            return null ;
	        }

	        return addEdge(node1, node2) ;
	    }
	    
	    public Edge addEdge(Node n1, Node n2)
	    {
	        return addEdge(new Edge(n1, n2)) ;
	    }

	    public Edge addEdge(Edge edge)
	    {
	    	if(!edge.getN1().equals(edge.getN2()))
	        {
	    		for(Edge e : this.getEdges())
	    		{
	    			if(e.getN1().equals(edge.getN1()) && e.getN2().equals(edge.getN2()))
	    			{
	    				return null ;
	    			}
	    		}
	        	if (!graph.get(edge.getN1()).add(edge))
	        	{
	        		return null ;
	        	}
	        	return edge ;
	        }
	        return null ;
	    }
	    
	    public boolean removeEdge(String label1, String label2)
	    {
	        return removeEdge(new Node(label1), new Node(label2)) ;
	    }

	    public boolean removeEdge(Node n1, Node n2)
	    {
	        return removeEdge(new Edge(n1, n2)) ;
	    }

	    public boolean removeEdge(Edge edge)
	    {
	        if (!graph.get(edge.getN1()).remove(edge))
	        {
	            return false ;
	        }
	        return true ;
	    }
	    

	}
