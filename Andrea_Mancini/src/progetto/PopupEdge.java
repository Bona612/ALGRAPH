package progetto;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;


public class PopupEdge {
	
	private ContextMenu popup;
	public MenuItem item1;
	public MenuItem item2;
	public MenuItem item3;
    
    public PopupEdge() {
    	  popup = new ContextMenu();
    	  popup.setStyle("-fx-selection-bar: black; -fx-border-color: black;" );
          item1 = new MenuItem("Cambia Peso");
          item2 = new MenuItem ("Modifica Arco");
          item3 = new MenuItem ("Elimina Arco");
          
          popup.getItems().addAll(item1, item2, item3);
          
    }
    
    
    public MenuItem getItem1() {
    	return item1;
    }
    
    public MenuItem getItem2() {
    	return item2;
    }
    
    public ContextMenu getPopup() {
    	return this.popup;
    }
}
