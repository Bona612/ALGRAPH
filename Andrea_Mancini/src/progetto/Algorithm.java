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
    
    public Algorithm(Node node, Graph graph)
    {
    	this.graph = new Graph() ;
    	this.graph.setGraph(graph.getGraph()) ;
    	this.priorityQueue = new PriorityQueue<Node>(graph.getNodes().size()) ;
    	this.start(node) ;
    }
    
    public void start(Node node)
    {
    	u = null ;
        v = null ;
        adjacencies = null ;
        startNode = node ;
        resultDistance = this.graph.getNodes().stream().collect(Collectors.toMap(n -> n, n -> n.equals(this.startNode) ? 0 : Integer.MAX_VALUE)) ;
        resultParent = new HashMap<>() ;
        this.priorityQueue.insert(startNode, 0) ;
    }
    
    /*public void executeStep()
    {
    	switch(step)
    	{
    		case 0:
    			this.priorityQueue.insert(startNode, 0) ;
    			break;
    		case 1:
    			if(priorityQueue.isEmpty())
    			{
    				this.step = STEP_END ;
    				return ;
    			}
    			break ;
    		case 2:
    			PriorityItem<Node> priorityItem = priorityQueue.deleteMax() ;
    			u = priorityItem.getItem() ;
    			//graphController.highlight(u, resultParent.get(u));
    			//u.getUi().getDistanceLabel().setText(String.valueOf(resultDistance.get(u)));
    			break;
    		case 3:
    			if(this.adjacencies == null) 
    			{
    				this.adjacencies = this.graph.getGraph().get(u).iterator();
    			}
    			if(!this.adjacencies.hasNext())
    			{
    				this.step = 1 ;
    				this.adjacencies = null ;
    				return ;
    			}
    			this.e = this.adjacencies.next() ;
    			this.v = this.e.getN2() ;
    			this.w = this.e.getWeight() ;
    			break ;
    		case 4:
    			if(resultDistance.get(u) + w >= resultDistance.get(v))
    			{
    				step = 3 ;
    				return ;
    			}
    			break ;
    		case 5:
    			if(priorityQueue.searchItem(v)) 
    			{
    				this.step = 7 ;
    				return ;
    			}	
    			break ;
    		case 6:
    			priorityQueue.insert(v, resultDistance.get(u) + w) ;
    			this.step = 8 ;
    			return ;
    		case 7:
    			priorityQueue.increase(priorityQueue.getPriorityItem(v), resultDistance.get(u) + w) ;
    			break ;
    		case 8:
    			resultDistance.put(v, resultDistance.get(u) + w) ;
    			break ;
    		case 9:
    			resultParent.put(v, u) ;
    			this.step = 3 ;
    			return ;
    		case 10:
    			//priorityQueueController.clear() ;
    			break ;
    	}
    	this.step++;
    }*/
    
    public boolean executeStep()
    {
    	if(priorityQueue.isEmpty())
    	{
    		PriorityItem<Node> priorityItem = priorityQueue.deleteMax() ;
    		System.out.println(priorityItem.getPriority()) ;
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
						priorityQueue.increase(priorityQueue.getPriorityItem(v), resultDistance.get(u) + w) ;
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
