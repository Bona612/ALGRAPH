package progetto;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ItemGraphic<T> extends Group
{
	private PriorityItem<T> priorityItem ;
    private Rectangle rectangle ;
    private Label nodeLabel ;
    private Label priorityLabel ;
    //private Label emptyLabel ;
    private static final int height = 100;
    private static final int width = 100 ;
    
    public ItemGraphic()
    {
    }
    
    public ItemGraphic(PriorityItem<T> priorityItem)
    {
        this.priorityItem = priorityItem;
        this.rectangle = new Rectangle(width, height) ;
        this.rectangle.setStroke(Color.BLACK) ;
        this.rectangle.setFill(Color.RED) ;
        
        Node node = getNode();
        
        nodeLabel = new Label((node != null) ? node.getLabel() : "");
        nodeLabel.setLabelFor(this);
        nodeLabel.setTextFill(Color.WHITE);
        nodeLabel.setPrefWidth(rectangle.getWidth());
        nodeLabel.setPrefHeight(rectangle.getHeight());
        nodeLabel.setFont(new Font(24)) ;
        nodeLabel.setAlignment(Pos.TOP_CENTER) ;
        
        priorityLabel = new Label((priorityItem != null) ? (Integer.toString(priorityItem.getPriority())) : "");
        priorityLabel.setLabelFor(this);
        priorityLabel.setTextFill(Color.WHITE);
        priorityLabel.setPrefWidth(rectangle.getWidth());
        priorityLabel.setPrefHeight(rectangle.getHeight());
        priorityLabel.setFont(new Font(24)) ;
        priorityLabel.setAlignment(Pos.BOTTOM_CENTER) ;
        
/*
        if(getNode() == null){
            initEmptyLabel();
        }
*/
        this.getChildren().addAll(rectangle,nodeLabel,priorityLabel);
/*
        if(emptyLabel != null){
            this.getChildren().add(emptyLabel);
        }*/
    }
    
    public void setPriorityLabel()
    {
    	this.priorityLabel.setText(Integer.toString(priorityItem.getPriority())) ;
    }
    
    private Node getNode()
    {
        if(priorityItem != null)
        {
            if (priorityItem.getItem() instanceof Node)
            {
                return (Node) priorityItem.getItem() ;
            }
        }
        return null ;
    }
    
    public PriorityItem<T> getPriorityItem() 
    {
        return priorityItem ;
    }
    
}
