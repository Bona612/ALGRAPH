package ProjectJohnson;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class PriorityQueue<T>
{
	private ArrayList<PriorityItem<T>> list ;
    private HBox graphicQueue;
	private int capacity ;
	private int dim ;

	public PriorityQueue() 
	{
		this.list = new ArrayList<>() ;
		setGraphicQueue();
	}
	
	public PriorityQueue(int n) 
	{
		this.list = new ArrayList<>() ;
		this.capacity = n ;
		this.dim = 0 ;
		this.list.add(new PriorityItem<T>()) ;
		setGraphicQueue();
		
	}
	
	private void setGraphicQueue() {
		this.graphicQueue = new HBox();
		this.graphicQueue.setAlignment(Pos.CENTER);
		this.graphicQueue.setPrefSize(900.0, 65.0);
	}
	
	public void resetQueue()
	{
		this.clear();
		this.dim = 0 ;
		this.list.add(new PriorityItem<T>()) ;
		this.graphicQueue.getChildren().clear();
	}
	
	public void incCapacity()
	{
		this.capacity++ ;
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
	
	public void minHeapRestore(ArrayList<PriorityItem<T>> list, int i, int dim)
	{
		int min = i ;
		if(((2 * i) <= dim) && (this.list.get(2 * i).getPriority() < this.list.get(min).getPriority()))
		{
			min = 2 * i ;
		}
		if((((2 * i) + 1) <= dim) && (this.list.get((2 * i) + 1).getPriority() < this.list.get(min).getPriority()))
		{
			min = (2 * i) + 1 ;
		}
		if(min != i)
		{
			this.swap(i, min) ;
			this.minHeapRestore(list, min, dim) ;
		}
	}
	
	public void swap(int pos1, int pos2)
	{
		PriorityItem<T> aux = new PriorityItem<T>() ;
		
		aux.setItem(this.list.get(pos1).getItem()) ;
		aux.setPriority(this.list.get(pos1).getPriority()) ;
		
		this.list.get(pos1).setItem(this.list.get(pos2).getItem()) ;
		this.list.get(pos1).setPriority(this.list.get(pos2).getPriority()) ;
		
		this.list.get(pos2).setItem(aux.getItem()) ;
		this.list.get(pos2).setPriority(aux.getPriority()) ;
		
		this.setGraphicQueue(pos1 - 1);
		this.setGraphicQueue(pos2 - 1);
		
		((ItemGraphic<T>)(this.graphicQueue.getChildren().get(pos1 - 1))).setColor(((ItemGraphic<T>)(this.graphicQueue.getChildren().get(pos2 - 1))).getColor());
		((ItemGraphic<T>)(this.graphicQueue.getChildren().get(pos2 - 1))).setColor(((ItemGraphic<T>)(this.graphicQueue.getChildren().get(pos1 - 1))).getColor());

		/*((ItemGraphic<T>)(this.graphicQueue.getChildren().get(pos1 - 1))).setPriorityLabel();
		((ItemGraphic<T>)(this.graphicQueue.getChildren().get(pos1 - 1))).setNodeLabel();
		((ItemGraphic<T>)(this.graphicQueue.getChildren().get(pos2 - 1))).setPriorityLabel();
		((ItemGraphic<T>)(this.graphicQueue.getChildren().get(pos2 - 1))).setNodeLabel();*/
	}
	
	public void heapBuild(ArrayList<PriorityItem<T>> list, int n)
	{
		for(int i = n / 2; i > 1; i--)
		{
			this.minHeapRestore(list, i, n) ;
		}
	}

	public PriorityItem<T> getMin()
	{
		if(this.dim > 0)
		{
			return this.list.get(1) ;
		}
		return null ;
	}
	
	public PriorityItem<T> deleteMin()
	{
		this.swap(1, dim) ;
		this.dim-- ;
		this.minHeapRestore(this.list, 1, dim) ;
		PriorityItem<T> tmp = this.list.get(this.dim + 1) ;
		this.list.remove(this.dim + 1) ;
		this.graphicQueue.getChildren().remove(this.dim);
		return tmp ;
	}
	
	public PriorityItem<T> insert(T x, int p)
	{
		if(dim < capacity)
		{
			this.dim++ ;
			PriorityItem<T> tmp = new PriorityItem<T>(x, p, dim) ;
			this.list.add(dim, tmp) ;
			this.graphicQueue.getChildren().add(this.dim - 1, new ItemGraphic<T>(tmp));
			int i = dim ;
			while((i > 1) && (this.list.get(i).getPriority() < this.list.get(i / 2).getPriority()))
			{
				this.swap(i, i / 2) ;
				i = i / 2 ;
			}
			return this.list.get(i) ;
		}
		return null ;
	}
	
	public void decrease(PriorityItem<T> item, int p)
	{
		if(p < item.getPriority())
		{
			item.setPriority(p) ;
			int i = item.getPos() ;
			this.setGraphicQueue(i - 1);
			//((ItemGraphic)(this.graphicQueue.getChildren().get(i - 1))).setPriorityLabel();
			while((i > 1) && (this.list.get(i).getPriority() < this.list.get(i / 2).getPriority()))
			{
				this.swap(i, i / 2) ;
				i = i / 2 ;
			}
		}
	}
	
	public T delete(PriorityItem<T> item, int p)
	{
		item.setPriority(-1) ;
		this.deleteMin() ;
		return item.getItem() ;
	}
	
	public boolean searchItem(T item)
	{
		boolean searched = false ;
        for(PriorityItem<T> tmp : this.list)
        {
        	if((Node)(tmp.getItem()) != null)
        	{
        		if(((Node)(tmp.getItem())).getLabel().equals(((Node)item).getLabel()))
                {
            		searched = true ;
                }
        	}
        }
        return searched ;
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
	
	public HBox getGraphicQueue() {
		return this.graphicQueue;
	}
	
	public void setGraphicQueue(int i)
	{
		((ItemGraphic<T>)(this.graphicQueue.getChildren().get(i))).setNodeLabel();
		((ItemGraphic<T>)(this.graphicQueue.getChildren().get(i))).setPriorityLabel();
	}
	
	public void setGraphicQueueColor(int i, Color color)
	{
		((ItemGraphic<T>)(this.graphicQueue.getChildren().get(i))).setColor(color);
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
				stringBuilder.append(" ") ;
				stringBuilder.append(item.getPos()) ;
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