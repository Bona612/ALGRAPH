package algorithm ;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import model.Edge;
import model.Graph;
import model.Node;
import model.PriorityItem;
import model.PriorityQueue;

public class Algorithm
{
	private int step ;
	private static int STEP_END = 10 ;
	
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
    
    public Algorithm(Node node)
    {
    	this.priorityQueue = new PriorityQueue<Node>() ;
    	this.start(node) ;
    }
    
    public void start(Node node)
    {
    	step = 0 ;
    	u = null ;
        v = null ;
        adjacencies = null ;
        resultDistance = this.graph.getNodes().stream().collect(Collectors.toMap(n -> n, n -> n.equals(this.startNode) ? 0 : Integer.MAX_VALUE)) ;
        resultParent = new HashMap<>() ;
        this.executeStep() ;
    }
    
    public void executeStep()
    {
    	switch (step)
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
    }
    
    public void executeAll() 
    {
        while (this.step <= STEP_END)
        {
            executeStep();
        }
    }
    
}
