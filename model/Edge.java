package model;

import java.util.Objects;

public class Edge
{
	private final Node n1 ;
	private final Node n2 ;
	private int weight ;
	
	public Edge(Node n1, Node n2)
	{
		this.n1 = n1 ;
		this.n2= n2 ;
		this.weight = -1 ;
	}

	public Edge(Node n1, Node n2, int weight)
	{
		this.n1 = n1 ;
		this.n2= n2 ;
		this.weight = weight ;
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
    	this.weight = weight ;
    }
    
    public int getWeight()
    {
        return weight;
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
