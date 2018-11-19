package ProjectJohnson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.scene.paint.Color;

public class Algorithm
{
	private Node startNode;
	private Map<Node, Integer> resultDistance;
    private Map<Node, Node> resultParent;
    private Node u;
    private Iterator<Edge> adjacencies;
    private Node v;
    private int w;
    private Edge e;
    private Graph graph;
    private PriorityQueue<Node>	priorityQueue;
    private int step;
    private GraphicControl graphicControl;
    private boolean isSet;
    private boolean wantInterrupt;
    private boolean pause;
    private boolean automatic;
    private boolean istant;
    
    public Algorithm(GraphicControl gc)
    {
    	this.graphicControl = gc;
    	this.setControlGraph();
    }
    
    public void setControlGraph()
    {
    	this.graph = new Graph() ;
    	this.graph.setGraph(this.graphicControl.getGraph().getGraph()) ;
    	this.priorityQueue = new PriorityQueue<Node>(graph.getNodes().size()) ;
    	this.startNode = null;
    	this.u = null ;
        this.v = null ;
        this.adjacencies = null ;
        this.resultParent = new HashMap<>() ;
        this.step = 0 ;
        this.pause = false;
        this.isSet = false;
        this.automatic = false;
        this.wantInterrupt = false;
        this.istant = false;
    }
    
    public void setStartNode(Node node)
    {	
    	this.step++ ;
        startNode = node ;
        startNode.setPriority(0) ;
        resultDistance = this.graph.getNodes().stream().collect(Collectors.toMap(n -> n, n -> n.equals(this.startNode) ? 0 : Integer.MAX_VALUE)) ;
        this.priorityQueue.insert(startNode, 0) ;
    }
    
    public void executeStep()
    {
    	/*
    	if (this.wantInterrupt == true) {
    		setButton(false);
    		graphicControl.getUtilityButton().getRestart().setDisable(true);
    		this.wantInterrupt = false;
    	}
    	*/
    
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
    				if(priorityQueue.getPriorityItem(n_tmp) != null)
    				{
    					if(priorityQueue.getPriorityItem(n_tmp).getPos() != -1)
        				{
        					priorityQueue.setGraphicQueueColor(priorityQueue.getPriorityItem(n_tmp).getPos() - 1, Color.WHITE) ;
        				}
    				}
    			}
    			if(priorityQueue.isEmpty())
               	{
    				System.out.println(priorityQueue.toString()) ;
    				PriorityItem<Node> priorityItem = priorityQueue.deleteMin() ;
            		u = priorityItem.getItem() ;
            		u.setPriority(resultDistance.get(u)) ;
            		System.out.println(priorityQueue.toString()) ;
            		u.setColor(Color.LAWNGREEN) ;
            		if(v != null)
            		{
            			this.adjacencies = this.graph.getGraph().get(resultParent.get(u)).iterator() ;
                		while(this.adjacencies.hasNext())
                		{
                			Edge tmp = this.adjacencies.next() ;
                			if(tmp.getN2().equals(u))
                			{
                				tmp.getArrow().setColor(Color.LAWNGREEN) ;
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
    		case 3 :
    			System.out.println("Finish") ;
    			UtilityWindow.finishWindow();
    			break ;
    		default :
    			return;
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
		if(priorityQueue.searchItem(v))
		{
			priorityQueue.setGraphicQueueColor(priorityQueue.getPriorityItem(v).getPos() - 1, Color.RED);
		}
		for(Edge e2 : this.graph.getEdges())
		{
			if(e2.getN1().equals(u) && e2.getN2().equals(v))
			{
				e2.getArrow().highlight(Color.RED) ;
				e2.getN2().highlight(Color.RED) ;
			}
		}
    }
    
    public boolean isFinished()
    {
    	return this.step == 3 ;
    }
    
    public void executeAll()
    {
    	if (isSet == false)
    		this.isSet = true;
    	
    	if(pause)
    	{
    		this.pause = false ;
    		return ;
    	}
    	
    	if (this.wantInterrupt == true)
    	{
    		this.automatic = false;
    		this.wantInterrupt = false;
    		return;
    	}
    	
    	setButton(this.automatic);
    	
    	if(this.isFinished())
    	{

    		setButton(true);
    		this.executeStep();
    		return;
    	
    	}
    	
    	if(this.automatic)
    	{
    		if(this.istant) {
    			this.executeStep();
    			this.executeAll();
    		}
    		else {
    			Timeout.setTimeout(() -> { this.executeStep() ; }, 1000, () -> { this.executeAll() ; }) ;
    		}
    	}
    	else
    	{
    		this.executeStep();
    		
    		if(this.isFinished()) {
    			this.executeAll();
    		}
    	}
    }
    
    private void setButton(boolean run) {
  	
		graphicControl.getUtilityButton().getPasso().setDisable(this.isFinished() ? true : run);
		graphicControl.getUtilityButton().getPause().setDisable(this.isFinished() ? true : !run);
		graphicControl.getUtilityButton().getEsegui().setDisable(this.isFinished() ? true : run);
	 	graphicControl.getUtilityButton().getRestart().setDisable(this.isFinished() ? false : run);
    }
    
    
    public void setPause()
    {
    	pause = true;
    	this.automatic = false;
    }
    
    public void resetAlgorithm()
    {
    	this.startNode = null ;
    	this.u = null ;
    	this. v = null ;
        this.adjacencies = null ;
        this.step = 0 ;
        this.isSet = false;
        this.pause = false ;
        this.istant = false;
        this.resultDistance.clear() ;
        this.resultParent.clear() ;
        this.priorityQueue.resetQueue() ;
        this.automatic = false;
        setButton(false);
	 	graphicControl.getUtilityButton().getRestart().setDisable(true);

        for (Node tmp_n : this.graph.getNodes())
        {
        	tmp_n.setColor(Color.WHITE);
        	tmp_n.setPriority();
        }
        
        for (Edge tmp_e : this.graph.getEdges())
        	tmp_e.getArrow().setColor(Color.WHITE);
    }
    
    public boolean getIsSet()
    {
    	return this.isSet;
    }
    
    public void setAutomatic() {
    	this.automatic = true;
    }
    
    public PriorityQueue<Node> getQueue() {
    	return this.priorityQueue;
    }
    
    public void interruptAlgorithm() {
    	setButton(false);
		graphicControl.getUtilityButton().getRestart().setDisable(true);
    	wantInterrupt = true;
    }
    
}