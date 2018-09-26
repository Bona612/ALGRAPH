package progetto;

import java.util.Objects;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


public class Node {
	
	//private Circle cerchioTop; 
	private Circle circle;
	public PopupNode popup;
	private Color color ;
	private StackPane sp = new StackPane();
	private double controlNodeX, controlNodeY;
	private Label textLabel ;
	private Label priorityLabel ;
	private String label ;
	
	public Node()
	{
	
		this.label = "" ;
		NodeGraphic();
	}
	
	public Node(String label)
	{
		this.setLabel(label) ;
		NodeGraphic();
	}
	
	private void NodeGraphic() {
		this.color = Color.BLACK ;
		circle = new Circle (24.0, Color.BLACK);
		circle.setStroke(Color.BLACK) ;
		circle.setStrokeWidth(1.5) ;
		
		this.textLabel = new Label(this.label) ;
		textLabel.setFont(new Font(16)) ;
		textLabel.setTextFill(Color.WHITE) ;
		textLabel.setPrefWidth(this.circle.getRadius() * 2);
		textLabel.setPrefHeight(this.circle.getRadius() * 2);
		textLabel.setAlignment(Pos.TOP_CENTER) ;
		
		this.priorityLabel = new Label("") ;
		priorityLabel.setFont(new Font(16)) ;
		priorityLabel.setTextFill(Color.WHITE) ;
		priorityLabel.setPrefWidth(this.circle.getRadius() * 2);
		priorityLabel.setPrefHeight(this.circle.getRadius() * 2);
		priorityLabel.setAlignment(Pos.BOTTOM_CENTER) ;
		/*Text t1 = new Text(label);
		Font f1 = new Font ("Arial", 14);
		
		t1.setFill(this.color);
		t1.setFont(f1);*/
		
				
		//cerchioTop =  new Circle (25.0, Color.WHITE);
		
		//cerchioBack.setCenterX(cerchioTop.getCenterX());
		//cerchioBack.setCenterY(cerchioTop.getCenterY());
		sp.getChildren().addAll(circle, textLabel, priorityLabel);
		sp.setMaxSize(this.circle.getRadius() * 2, this.circle.getRadius() * 2);
		sp.setMinSize(5.0, 5.0);
		sp.setCursor(Cursor.HAND);
		sp.translateXProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ObservableValue, Number oldSceneWidth, Number newX) {
				controlNodeX = (double)newX;
				
			}
			
				
		});
		sp.translateYProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> ObservableValue, Number oldSceneWidth, Number newY) {
				controlNodeY = (double)newY;
				
			}
			
				
		});
		
		setPopup();
		
	}
	
	private void setPopup() {
		
	    this.popup = new PopupNode(); 
	}
	
	public ContextMenu getPopup() {
		return this.popup.getPopup();
	}
	
	public void setLabel(String label)
	{
		this.label = label ;
	}
	
	public String getLabel()
	{
		return this.label ;
	}
	
	public StackPane getNodeSP() {
		return this.sp;
	}
	
	public double getRadiusCircle() {
		return circle.getRadius();
	}
	
	
	public Circle getCircle() {
		return circle;
	}
	
	public double getX() {
		return controlNodeX;
	}
	
	public double getY() {
		return controlNodeY;
	}
	
	public void highlight(Color color)
	{
		this.circle.setFill(color) ;
	}
	
	public void setColor(Color color)
	{
		this.color = color ;
		this.highlight(this.color) ;
	}
	    
	public Color getColor()
	{
		return this.color ;
	}
	
	public void resetHighlight()
    {
        highlight(this.color) ;
    }
	
	public void setPriority(int priority)
	{
		this.priorityLabel.setText(Integer.toString(priority)) ; 
	}
	
	@Override
	public String toString()
	{
		return "Node{" + "label" + this.label + "}" ;
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