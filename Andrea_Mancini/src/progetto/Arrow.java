package progetto;

import javafx.beans.InvalidationListener;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color; 

public class Arrow extends Group
{
    private final Line line;
    private static final double DistanceLab = 10;
    private static final double distanceLine = 5;
    private static final double arrowLength = 10;
    private static final double arrowWidth = 7;
    private Label label;
    private Color color;
    private Polygon arrow;
    private Edge edge;
    
    public Arrow(Edge edge)
    {
    	this(new Line(), new Polygon(), edge) ;    
    }
  
  public Arrow(Line line, Polygon arrow, Edge edge) 
  {
	  super(line, arrow);
        this.line = line;
        this.arrow = arrow;
        this.edge = edge;
        this.color = Color.BLACK ;
        this.label = new Label(String.valueOf(this.edge.getWeight()));
        	
        setStartX(this.edge.getN1().getNodeSP().getTranslateX()+28.0);     
        setStartY(this.edge.getN1().getNodeSP().getTranslateY()+28.0);
        setEndX(this.edge.getN2().getNodeSP().getTranslateX()+28.0);
        setEndY(this.edge.getN2().getNodeSP().getTranslateY()+28.0);
        
        this.line.setStrokeWidth(1);
        
        label.setTextFill(this.color) ;
        label.setAlignment(Pos.CENTER); 
        label.setLabelFor(this);
        label.setFont(new Font(16));
        
        label.widthProperty().addListener(updater); 
        label.heightProperty().addListener(updater);
      
        this.edge.getN1().getNodeSP().translateXProperty().addListener(updater);
        this.edge.getN1().getNodeSP().translateYProperty().addListener(updater);
        this.edge.getN2().getNodeSP().translateXProperty().addListener(updater);
        this.edge.getN2().getNodeSP().translateYProperty().addListener(updater);
        
        startXProperty().addListener(updater);
        startYProperty().addListener(updater);
        endXProperty().addListener(updater);
        endYProperty().addListener(updater);
        updater.invalidated(null);
        
        this.getChildren().add(this.label);
        
  }
  
  InvalidationListener updater = o -> {
	
  	double circonf = this.edge.getN2().getBack().getRadius();

      double
              arrowsx = this.edge.getN1().getNodeSP().getTranslateX()+28.0,
              arrowsy = this.edge.getN1().getNodeSP().getTranslateY()+28.0,
              arrowex = this.edge.getN2().getNodeSP().getTranslateX()+28.0,
              arrowey = this.edge.getN2().getNodeSP().getTranslateY()+28.0,
              arrowdx = arrowsx - arrowex,
              arrowdy = arrowsy - arrowey;

      double dist = Math.hypot(arrowdx, arrowdy);

      arrowdx /= dist;
      arrowdy /= dist;

      
      arrowsx = arrowsx + distanceLine * arrowdy;
      arrowsy = arrowsy - distanceLine * arrowdx;
      arrowex = arrowex + distanceLine * arrowdy;
      arrowey = arrowey - distanceLine * arrowdx;

      

      setStartX(arrowsx);
      setStartY(arrowsy);
      setEndX(arrowex);
      setEndY(arrowey);

      double middleX = (arrowsx + arrowex) / 2;
      double middleY = (arrowsy + arrowey) / 2;
      
      double labelX = middleX + DistanceLab * arrowdy - label.getWidth() / 2;
      double labelY = middleY - DistanceLab * arrowdx - label.getHeight() / 2;
    
      label.setTranslateX(labelX);
      label.setTranslateY(labelY);


	  double distance = Math.hypot(arrowex - arrowsx, arrowey - arrowsy);
	  double t = (distance - circonf + 1) / distance;

      double arrowsEx = (1 - t) * arrowsx + t * arrowex;
      double arrowsEy = (1 - t) * arrowsy + t * arrowey;

 

      double parallelComponent = arrowLength / (distance - circonf);
      double orthogonalComponent = arrowWidth / (distance - circonf);

	  arrowdx = (arrowsx - arrowsEx) * parallelComponent;
	  arrowdy = (arrowsy - arrowsEy) * parallelComponent;
		
	  double ox = (arrowsx - arrowsEx) * orthogonalComponent;
	  double oy = (arrowsy - arrowsEy) * orthogonalComponent;
	  
	  arrow.getPoints().setAll(new Double[]{
	  		arrowsEx, arrowsEy,
	  		arrowsEx + arrowdx - oy, arrowsEy + arrowdy + ox,
	  		arrowsEx + arrowdx + oy, arrowsEy + arrowdy - ox
		           });
		  
          
  };

    public final void setStartX(double value) {
        line.setStartX(value);
    }

    public final double getStartX() {
        return line.getStartX();
    }

    public final DoubleProperty startXProperty() {
        return line.startXProperty();
    }

    public final void setStartY(double value) {
        line.setStartY(value);
    }

    public final double getStartY() {
        return line.getStartY();
    }

    public final DoubleProperty startYProperty() {
        return line.startYProperty();
    }

    public final void setEndX(double value) {
        line.setEndX(value);
    }

    public final double getEndX() {
        return line.getEndX();
    }

    public final DoubleProperty endXProperty() {
        return line.endXProperty();
    }

    public final void setEndY(double value) {
        line.setEndY(value);
    }

    public final double getEndY() {
        return line.getEndY();
    }
    
    public void setLabel(int weight) {
   		this.label.setText(String.valueOf(weight)) ;
    }

    public final DoubleProperty endYProperty() {
        return line.endYProperty();
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
    
    public void highlight(Color color)
    {
    	line.setStroke(color) ;
        label.setTextFill(color) ;
        arrow.setStroke(color) ;
        arrow.setFill(color) ;
    }
    
}
