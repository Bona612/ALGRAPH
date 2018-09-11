package progetto;

import java.util.ArrayList;

import progetto.Node;
import progetto.PriorityItem;

public class PriorityQueue<T>
{
	private ArrayList<PriorityItem<T>> list ;
	private int capacity ;
	private int dim ;

	public PriorityQueue() 
	{
		this.list = new ArrayList<>() ;
	}
	
	public PriorityQueue(int n) 
	{
		this.list = new ArrayList<>() ;
		this.capacity = n ;
		this.dim = 0 ;
		this.list.add(new PriorityItem<T>()) ;
	}
	
	public void clear()
	{
		list.clear() ;
	}

	public ArrayList<PriorityItem<T>> getList()
	{
		return this.list ;
	}

	public void setList(ArrayList<PriorityItem<T>> list)
	{
		this.list = list ;
	}
	
	public boolean isEmpty() 
	{
		return this.dim > 0 ;
	}
	
	public void maxHeapRestor(ArrayList<PriorityItem<T>> list, int i, int dim)
	{
		int max = i ;
		if(((2 * i) <= dim) && (this.list.get(2 * i).getPriority() > this.list.get(max).getPriority()))
		{
			max = 2 * i ;
		}
		if((((2 * i) + 1) <= dim) && (this.list.get((2 * i) + 1).getPriority() > this.list.get(max).getPriority()))
		{
			max = (2 * i) + 1 ;
		}
		if(max != i)
		{
			this.swap(i, max) ;
			this.maxHeapRestor(list, max, dim) ;
		}
	}
	
	public void swap(int pos1, int pos2)
	{
		PriorityItem<T> aux = new PriorityItem<T>() ;
		aux.setItem(this.list.get(pos1).getItem()) ;
		aux.setPriority(this.list.get(pos1).getPriority()) ;
		aux.setPos(this.list.get(pos1).getPos()) ;
		this.list.get(pos1).setItem(this.list.get(pos2).getItem()) ;
		this.list.get(pos1).setPriority(this.list.get(pos2).getPriority()) ;
		this.list.get(pos1).setPos(this.list.get(pos2).getPos()) ;
		this.list.get(pos2).setItem(aux.getItem()) ;
		this.list.get(pos2).setPriority(aux.getPriority()) ;
		this.list.get(pos2).setPos(aux.getPos()) ;
	}
	
	public void heapBuild(ArrayList<PriorityItem<T>> list, int n)
	{
		for(int i = n / 2; i > 1; i--)
		{
			this.maxHeapRestor(list, i, n) ;
		}
	}
	
	public PriorityItem<T> getMax()
	{
		if(this.dim > 0)
		{
			return this.list.get(1) ;
		}
		return null ;
	}
	
	public PriorityItem<T> deleteMax()
	{
		this.swap(1, dim) ;
		this.dim-- ;
		this.maxHeapRestor(this.list, 1, dim) ;
		PriorityItem<T> tmp = this.list.get(this.dim + 1) ;
		this.list.remove(this.dim + 1) ;
		return tmp ;
	}
	
	public PriorityItem<T> insert(T x, int p)
	{
		if(dim < capacity)
		{
			this.dim++ ;
			PriorityItem<T> tmp = new PriorityItem<T>(x, p, dim) ;
			this.list.add(dim, tmp) ;
			int i = dim ;
			while((i > 1) && (this.list.get(i).getPriority() > this.list.get(i / 2).getPriority()))
			{
				this.swap(i, i / 2) ;
				i = i / 2 ;
			}
			return this.list.get(i) ;
		}
		return null ;
	}
	
	public void increase(PriorityItem<T> item, int p)
	{
		if(p > item.getPriority())
		{
			item.setPriority(p) ;
			int i = item.getPos() ;
			while((i > 1) && (this.list.get(i).getPriority() > this.list.get(i / 2).getPriority()))
			{
				this.swap(i, i / 2) ;
				i = i / 2 ;
			}
		}
	}
	
	public T delete(PriorityItem<T> item, int p)
	{
		item.setPriority(1000) ;
		this.deleteMax() ;
		return item.getItem() ;
	}
	
	public boolean searchItem(T item)
	{
		PriorityItem<T> priorityItem = new PriorityItem<>(item) ;
        PriorityItem<T> searched = null ;
        for(PriorityItem<T> tmp : this.list)
        {
            if(priorityItem.equals(tmp))
            {
                searched = tmp ;
            }
        }
        return searched != null ;
    }
	
	public PriorityItem<T> getPriorityItem(T item)
	{
		PriorityItem<T> found = null ;
		PriorityItem<T> priorityItem = new PriorityItem<>(item) ;
        for(PriorityItem<T> tmp : this.list)
        {
            if(priorityItem.equals(tmp))
            {
                found = tmp ;
            }
        }
        return found ;
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
				if(numItems < this.dim)
				{
					stringBuilder.append(",") ;
				}
			}
		}
		stringBuilder.append("}") ;
		
		return stringBuilder.toString() ;
	}
	
}

