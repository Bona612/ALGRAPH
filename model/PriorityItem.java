package model;

public class PriorityItem<T>
{
    private T item ;
    private int priority ;

    public PriorityItem()
    {
    }

    public PriorityItem(T item, int priority)
    {
        this.setItem(item) ;
        this.priority = priority ;
    }

    public PriorityItem(T item)
    {
        this.setItem(item) ;
    }
    
    public T getItem()
    {
        return this.item ;
    }
    
    public void setItem(T item)
    {
        this.item = item;
    }
    
    public int getPriority() 
    {
        return this.priority ;
    }

    public void setPriority(int priority) 
    {
        this.priority = priority ;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!this.getClass().equals(obj.getClass()))
        {
            return false ;
        }
        if(obj instanceof PriorityItem)
        {
            PriorityItem item = (PriorityItem) obj ;
            return this.item.equals(item.getItem()) ;
        }
        return false ;
    }
}
