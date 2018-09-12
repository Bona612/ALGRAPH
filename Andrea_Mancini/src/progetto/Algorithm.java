package progetto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class Algorithm
{
	
	private Node startNode ;
	
	private Map<Node, Integer> resultDistance ;
    private Map<Node, Node> resultParent ;
    
    private Node u ;
    private Iterator<Edge> adjacencies ;
    private Node v ;
    private int w ;
    private Edge e ;
    
    private Graph graph ;
    private PriorityQueue<Node>	priorityQueue ;
    
    public Algorithm(Graph graph)
    {
    	this.graph = new Graph() ;
    	this.graph.setGraph(graph.getGraph()) ;
    	this.priorityQueue = new PriorityQueue<Node>(graph.getNodes().size()) ;
    	u = null ;
        v = null ;
        adjacencies = null ;
        resultParent = new HashMap<>() ;
    }
    
    public void setStartNode(Node node)
    {
        startNode = node ;
        resultDistance = this.graph.getNodes().stream().collect(Collectors.toMap(n -> n, n -> n.equals(this.startNode) ? 0 : Integer.MAX_VALUE)) ;
        this.priorityQueue.insert(startNode, 0) ;
    }
    
    public boolean executeStep()
    {
    	if(priorityQueue.isEmpty())
    	{
    		PriorityItem<Node> priorityItem = priorityQueue.deleteMin() ;
			u = priorityItem.getItem() ;
			if(this.adjacencies == null) 
			{
				this.adjacencies = this.graph.getGraph().get(u).iterator() ;
			}
			while(this.adjacencies.hasNext())
    		{
				this.e = this.adjacencies.next() ;
				this.v = this.e.getN2() ;
	    		this.w = this.e.getWeight() ;
				if(resultDistance.get(u) + w < resultDistance.get(v))
	    		{
					if(!priorityQueue.searchItem(v))
					{
						priorityQueue.insert(v, resultDistance.get(u) + w) ;
					}
					else
					{
						priorityQueue.decrease(priorityQueue.getPriorityItem(v), resultDistance.get(u) + w) ;
					}
					resultDistance.put(v, resultDistance.get(u) + w) ;
					resultParent.put(v, u) ;
	    		}
    		}
			this.adjacencies = null ;
			return true ;
    	}
    	return false ;
    }
    
    public void executeAll() 
    {
        while(this.executeStep())
        {
        }
        System.out.println("finish") ;
    }
    
}
