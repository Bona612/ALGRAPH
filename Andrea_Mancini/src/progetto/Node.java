package progetto;

import java.util.Objects;




import progetto.Node;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


import javafx.scene.Cursor;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class Node {
	
	private Circle cerchioTop; 
	private Circle cerchioBack;
	public PopupNode popup;
	private StackPane sp = new StackPane();
	private double controlNodeX, controlNodeY;

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
		Text t1 = new Text(label);
		Font f1 = new Font ("Arial", 14);
		t1.setFill(Color.BLACK);
		t1.setFont(f1);
		setPopup();
		cerchioTop =  new Circle (25.0, Color.WHITE);
		cerchioBack = new Circle (28.0, Color.BLACK);
		cerchioBack.setCenterX(cerchioTop.getCenterX());
		cerchioBack.setCenterY(cerchioTop.getCenterY());
		sp.getChildren().addAll(cerchioBack, cerchioTop, t1);
		sp.setMaxSize(56.0, 56.0);
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
		
	}
	
	private void setPopup() {
		
	    this.popup = new PopupNode(); 
	}
	
	public Popup getPopup() {
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
	
	public double getRadiusBack() {
		return cerchioBack.getRadius();
	}
	
	public double getRadiusTop() {
		return cerchioTop.getRadius();
	}
	
	public Circle getTop() {
		return cerchioTop;
	}
	public Circle getBack() {
		return cerchioBack;
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
