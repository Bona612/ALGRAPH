package model;

import java.util.ArrayList;

public class PriorityQueue<T>
{
	private ArrayList<PriorityItem<T>> list ;

	public PriorityQueue() 
	{
		this.list = new ArrayList<>() ;
	}
	
	public void push(T item, int priorita)
	{
		PriorityItem<T> priorityItem = new PriorityItem<> (item, priorita) ;
		this.list.add(priorityItem) ;
	}
	
	public PriorityItem<T> pop()
	{
		PriorityItem<T> max = searchMax() ;
		remove(max) ;
		return max ;
	}
	
	public PriorityItem<T> read(T item)
	{
		PriorityItem<T> priorityItem = new PriorityItem<> (item) ;
		return searchItem(priorityItem) ;
	}
	
	public boolean existIteam(T item) 
	{
		PriorityItem<T> priorityItem = new PriorityItem<>(item) ;
		return searchItem(priorityItem) != null ;
	}
	
	public void remove(PriorityItem<T> priorityItem) 
	{
		if(priorityItem != null)
		{
			this.list.remove(priorityItem) ;
		}
	}
	
	public void remove(T item) 
	{
		PriorityItem <T> deleteItem = read(item) ;
		if(deleteItem != null)
		{
			this.list.remove(deleteItem) ;
		}
	}
	
	public boolean isEmpty() 
	{
		return this.list.isEmpty() ;
	}
	
	public void update (T item, int priority)
	{
		PriorityItem<T> newItem = new PriorityItem<>(item) ;
		int i = searchPosItem(newItem) ;
		if (i != -1)
		{
			this.list.get(i).setPriority(priority) ;
		}
	}
	
	public void clear() {
		list.clear();
	}
	
	private PriorityItem<T> searchMax() 
	{
		PriorityItem <T> max = null ;
		for(PriorityItem<T> item : list)
		{
			if (max == null || item.getPriority() > max.getPriority()) 
			{
				max = item ;
			}
		}
		return max ;
	}
	
	private PriorityItem <T> searchItem (PriorityItem<T> priorityItem)
	{
		PriorityItem<T> searched = null ;
		for(PriorityItem<T> item:this.list) 
		{
			if(priorityItem.equals(item))
			{
				searched = item ;
			}
		}
		return searched ;
	}
	
	private int searchPosItem(PriorityItem<T> priorityItem) 
	{
		for(int i=0; i< this.list.size(); i++) 
		{
			if(list.get(i).equals(priorityItem))
			{
				return i ;
			}
		}
		return -1 ;
	}

	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder() ;
		stringBuilder.append("S = {") ;
		int numItems = 0 ;
		for(PriorityItem<T> item : list) 
		{
			if (item.getItem() instanceof Node)
			{
				numItems++ ;
				Node node = (Node) item.getItem() ;
				stringBuilder.append(node.getLabel()) ;
				if(numItems < list.size())
				{
					stringBuilder.append(",") ;
				}
			}
		}
		stringBuilder.append(" }") ;
		
		return stringBuilder.toString() ;
	}

	public ArrayList<PriorityItem<T>> getList()
	{
		return this.list ;
	}

	public void setList(ArrayList<PriorityItem<T>> list)
	{
		this.list = list ;
	}
}
