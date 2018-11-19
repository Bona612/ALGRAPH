package ProjectJohnson;




import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;

import javafx.scene.paint.Color; 

 

public class Arrow extends Group
{
	private final Line lineTop;
	private final Line lineBack;
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
    	this(new Line(), new Line(), new Polygon(), edge) ;    
    }
  
  public Arrow(Line lineT, Line lineB, Polygon arrow, Edge edge) 
  {
	  super(lineB, lineT, arrow);
        this.lineBack = lineB;
        this.lineTop = lineT;
        this.arrow = arrow;
        this.edge = edge;
        this.color = Color.BLACK ;
        this.label = new Label(String.valueOf(this.edge.getWeight()));
        this.setCursor(Cursor.HAND);
        
        setStartX(this.edge.getN1().getNodeSP().getTranslateX()+28.0);     
        setStartY(this.edge.getN1().getNodeSP().getTranslateY()+28.0);
        setEndX(this.edge.getN2().getNodeSP().getTranslateX()+28.0);
        setEndY(this.edge.getN2().getNodeSP().getTranslateY()+28.0);
        
        this.lineTop.setStrokeWidth(2);
        this.lineBack.setStrokeWidth(3);
        this.setColor(Color.WHITE);
        
        label.setTextFill(Color.BLACK) ;
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
        lineTop.setStartX(value);
        lineBack.setStartX(value);
        
    }

    public final double getStartX() {
        return lineTop.getStartX();
    }

    public final DoubleProperty startXProperty() {
        return lineTop.startXProperty();
    }

    public final void setStartY(double value) {
        lineTop.setStartY(value);
        lineBack.setStartY(value);
    }

    public final double getStartY() {
        return lineTop.getStartY();
    }

    public final DoubleProperty startYProperty() {
        return lineTop.startYProperty();
    }

    public final void setEndX(double value) {
        lineTop.setEndX(value);
        lineBack.setEndX(value);
        
    }

    public final double getEndX() {
        return lineTop.getEndX();
    }

    public final DoubleProperty endXProperty() {
        return lineTop.endXProperty();
    }

    public final void setEndY(double value) {
    	 lineTop.setEndY(value);
         lineBack.setEndY(value);
    }

    public final double getEndY() {
        return lineTop.getEndY();
    }
    
    public void setLabel(int weight) {
   		this.label.setText(String.valueOf(weight)) ;
    }

    public final DoubleProperty endYProperty() {
        return lineTop.endYProperty();
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
    	lineTop.setStroke(color) ;
        label.setTextFill((this.color == Color.WHITE) ? ((color == Color.RED) ? Color.RED : Color.BLACK) : this.color) ;
        arrow.setFill(color) ;
        arrow.setStroke(Color.BLACK) ;
    }
    
}