package progetto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.scene.paint.Color;

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
    private int step ;
    private boolean reset ;
    private boolean pause ;
    
    public Algorithm(Graph graph)
    {
    	this.graph = new Graph() ;
    	this.graph.setGraph(graph.getGraph()) ;
    	this.priorityQueue = new PriorityQueue<Node>(graph.getNodes().size()) ;
    	u = null ;
        v = null ;
        adjacencies = null ;
        resultParent = new HashMap<>() ;
        this.step = 0 ;
        this.reset = false ;
        this.pause = false ;
    }
    
    public void setStartNode(Node node)
    {
        startNode = node ;
        resultDistance = this.graph.getNodes().stream().collect(Collectors.toMap(n -> n, n -> n.equals(this.startNode) ? 0 : Integer.MAX_VALUE)) ;
        this.priorityQueue.insert(startNode, 0) ;
        this.step++ ;
    }
    
    public void executeStep()
    {
    	if(this.step > 3)
    	{
    		return ;
    	}
    	switch(this.step)
    	{
    		case 1 :
    			for(Edge e_tmp : this.graph.getEdges())
    			{
    				e_tmp.getArrow().resetHighlight() ;
    			}
    			for(Node n_tmp : this.graph.getNodes())
    			{
    				n_tmp.resetHighlight() ;
    			}
    			if(priorityQueue.isEmpty())
               	{
    				System.out.println(priorityQueue.toString()) ;
    				PriorityItem<Node> priorityItem = priorityQueue.deleteMin() ;
            		u = priorityItem.getItem() ;
            		u.setPriority(resultDistance.get(u)) ;
            		System.out.println(priorityQueue.toString()) ;
            		u.setColor(Color.RED) ;
            		if(v != null)
            		{
            			this.adjacencies = this.graph.getGraph().get(resultParent.get(u)).iterator() ;
                		while(this.adjacencies.hasNext())
                		{
                			Edge tmp = this.adjacencies.next() ;
                			if(tmp.getN2().equals(u))
                			{
                				tmp.getArrow().setColor(Color.RED) ;
                			}
                		}
                		this.adjacencies = null ;
            		}
            		if(this.adjacencies == null) 
            		{
            			this.adjacencies = this.graph.getGraph().get(u).iterator() ;
            		}
            		break ;
               	}
    			this.step = 3 ;
    			return ;
    		case 2 :
    			if(this.adjacencies.hasNext())
        		{
    				System.out.println(priorityQueue.toString()) ;
        			this.executeStepIn() ;
        			System.out.println(priorityQueue.toString()) ;
        			return ;
        		}
    			this.step = 1 ;
    			this.executeStep() ;
    			return ;
    	}
    	this.step++ ;
    }
    
    public void executeStepIn()
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
		for(Edge e2 : this.graph.getEdges())
		{
			if(e2.getN1().equals(u) && e2.getN2().equals(v))
			{
				e2.getArrow().highlight(Color.BLUE) ;
				e2.getN2().highlight(Color.BLUE) ;
			}
		}
    }
    
    public boolean isFinished()
    {
    	return this.step == 3 ;
    }
    
    public void executeAll()
    {
    	if(this.isFinished())
    	{
    		return ;
    	}
    	if(pause)
    	{
    		this.pause = false ;
    		return ;
    	}
    	if(reset)
    	{
    		this.resetAlgorithm() ;
    		return ;
    	}
    	Timeout.setTimeout(() -> { this.executeStep() ; }, 2000, () -> { this.executeAll() ; }) ;
    }
    
    public void setReset()
    {
    	this.reset = true ;
    }
 
    public void setPause()
    {
    	this.pause = true ;
    }
    
    public void resetAlgorithm()
    {
    	this.startNode = null ;
    	this.u = null ;
    	this. v = null ;
        this.adjacencies = null ;
        this.step = 0 ;
        this.reset = false ;
        this.pause = false ;
        this.resultDistance.clear() ;
        this.resultParent.clear() ;
        this.priorityQueue.clear() ;
    }
}
