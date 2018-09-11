package progetto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.Popup;

public class PopupNode {
	
	public Popup popup;
	public ListView<String> option;
    public ObservableList<String> items;
    
    public PopupNode() {
    	this.popup = new Popup();
    	this.option = new ListView<String> ();
    	this.items = FXCollections.<String>observableArrayList(
				"Aggiungi Nodo", "Rimuovi Nodo");
    	option.setItems(items);
 	    option.setPrefHeight(73.0);
 	    option.setPrefWidth(95.0);
 	    option.setStyle("-fx-background-color: white; -fx-background-insets: 0; -fx-selection-bar: black; -fx-border-color: black;"
 	    		+ " -fx-border-insets: 0; -fx-border-width: 0.5; ");
 	   
 	    popup.getContent().add(option);
 	    popup.setAutoHide(true);
 	    
    }
    
    public ObservableList<String> getItems() {
    	return this.items;
    }
    
    public Popup getPopup() {
    	return this.popup;
    }
}
