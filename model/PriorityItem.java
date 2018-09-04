package model;

public class PriorityItem<T>
{
    private T item ;
    private int priority ;
    private int pos ;

    public PriorityItem()
    {
    }

    public PriorityItem(T item, int priority, int pos)
    {
        this.setItem(item) ;
        this.priority = priority ;
        this.pos = pos ;
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
        this.item = item ;
    }
    
    public int getPriority() 
    {
        return this.priority ;
    }

    public void setPriority(int priority) 
    {
        this.priority = priority ;
    }
    
    public int getPos()
    {
        return this.pos ;
    }
    
    public void setPos(int pos)
    {
        this.pos = pos ;
    }
    
}
