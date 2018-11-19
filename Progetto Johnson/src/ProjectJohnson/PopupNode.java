package ProjectJohnson;


import javafx.scene.control.ContextMenu;

import javafx.scene.control.MenuItem;


public class PopupNode {
	
	public ContextMenu popup;
	public MenuItem item1;
	public MenuItem item2;
	public MenuItem item3;
    
    public PopupNode() {
    	this.popup = new ContextMenu();
    	popup.setStyle("-fx-selection-bar: black; -fx-border-color: black;" );
    	
	    item1 = new MenuItem ("Aggiungi arco");
	    item2 = new MenuItem ("Rimuovi nodo");
	      
	    popup.getItems().addAll(item1, item2);
 	    popup.setAutoHide(true);
 	    
    }
     
    public ContextMenu getPopup() {
    	return this.popup;
    }
    
}
