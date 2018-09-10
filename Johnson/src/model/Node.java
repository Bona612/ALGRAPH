package model;

import java.util.Objects;

public class Node
{
	private String label ;
	
	public Node()
	{
		this.label = "" ;
	}
	
	public Node(String label)
	{
		this.setLabel(label) ;
	}
	
	public void setLabel(String label)
	{
		this.label = label ;
	}
	
	public String getLabel()
	{
		return this.label ;
	}
	
	@Override
	public String toString()
	{
		return "Node{" + "label " + this.label + "}" ;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(this == o)
		{
			return true ;
		}
		if (o == null || this.getClass() != o.getClass())
		{
            return false;
        }
		
		return Objects.equals(this.getLabel(), ((Node) o).getLabel());
	}
	
}
