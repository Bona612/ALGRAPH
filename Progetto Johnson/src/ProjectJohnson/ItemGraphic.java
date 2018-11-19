package ProjectJohnson;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ItemGraphic<T> extends Group
{
	private PriorityItem<T> priorityItem ;
	private Color colorR;
    private Rectangle rectangle ;
    private Label nodeLabel ;
    private Label priorityLabel ;
    //private Label emptyLabel ;
    private static final int height = 65;
    private static final int width = 65 ;
    
    public ItemGraphic()
    {
    }
    
    public ItemGraphic(PriorityItem<T> priorityItem)
    {
    	
    	this.colorR = Color.WHITE;
        this.priorityItem = priorityItem;
        this.rectangle = new Rectangle(width, height) ;
        this.rectangle.setStroke(Color.BLACK) ;
        this.rectangle.setFill(this.colorR);
        
        Node node = getNode();
        
        nodeLabel = new Label((node != null) ? node.getLabel() : "");
        nodeLabel.setLabelFor(this);
        nodeLabel.setTextFill(Color.BLACK);
        nodeLabel.setPrefWidth(rectangle.getWidth());
        nodeLabel.setPrefHeight(rectangle.getHeight());
        nodeLabel.setFont(new Font(24)) ;
        nodeLabel.setAlignment(Pos.TOP_CENTER) ;
        nodeLabel.setStyle("-fx-border-color:black;");
        
        priorityLabel = new Label((priorityItem != null) ? (Integer.toString(priorityItem.getPriority())) : "");
        priorityLabel.setLabelFor(this);
        priorityLabel.setTextFill(Color.BLACK);
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
    
    
    public void setNodeLabel()
    {
    	this.nodeLabel.setText(((Node)(priorityItem.getItem())).getLabel()) ;
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
    
    public void setColor(Color color)
    {
    	this.colorR = color ;
    	this.highlight(this.colorR) ;
    }
    
    public Color getColor()
    {
    	return this.colorR ;
    }
    
    public void resetHighlight()
    {
        highlight(this.colorR) ;
    }
    
    public void highlight(Color color)
    {
    	this.rectangle.setFill(color) ;
    }
    
    
}
