package progetto;

import java.util.Objects;


import javafx.stage.Popup;

public class Edge {

	private PopupEdge popup;
	private Arrow arrow;
	private final Node n1 ;
	private final Node n2 ;
	private int weight ;


	private static int DEFAULT_WEIGHT = 10 ;
	
	public Edge(Node n1, Node n2)
	{
		this.n1 = n1 ;
		this.n2 = n2 ;
		this.weight = DEFAULT_WEIGHT ;
		/*this.arrow = new Arrow(this);
		setPopup();*/
	}

	public Edge(Node n1, Node n2, int weight)
	{
		this.n1 = n1 ;
		this.n2 = n2 ;
		this.weight = weight ;
		/*this.arrow = new Arrow(this);
		setPopup();*/
	}
	
	public Node getN1()
	{
        return n1;
    }

    public Node getN2()
    {
        return n2;
    }

    public void setWeight(int weight)
    {
    	if(weight > 0)
    	{
    		this.weight = weight;
    		//this.arrow.setLabel(weight);
    	}
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public Arrow getArrow() {
    	return this.arrow;
    }
    
    private void  setPopup() {
    	this.popup = new PopupEdge();
    }
    public Popup getPopup() {
    	return this.popup.getPopup();
    }
    
    
    @Override
    public String toString()
    {
        return "Edge{" + "n1=" + n1 + ", n2=" + n2 + ", weight=" + weight + "}" ;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Edge edge = (Edge) o;
        return Objects.equals(this.getN1(), edge.getN1()) && Objects.equals(this.getN2(), edge.getN2()) && Objects.equals(this.getWeight(), edge.getWeight()) ;
    }
}
