package ProjectJohnson;

import java.io.File;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UtilityWindow {
	
	public static void errorWindow(String title, String header, String context) {
	
	Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(context);

    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.showAndWait();
	
	}
	
	public static Optional<String> choiceWindow(String title, String context, Graph graph) {
		
		String[] value = graph.getNodes().stream().map(Node::getLabel).sorted(String::compareTo).toArray(String[]::new);
		ChoiceDialog<String> dialog = new ChoiceDialog<>(value[0], value);
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(context);
               
        return dialog.showAndWait();
	}

	public static Optional<String> inputWindow (String title, String input, String context){
		TextInputDialog dialog = new TextInputDialog(input);
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(context);

        return dialog.showAndWait();
    }
	
	public static void finishWindow() {
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Algorithm");
	    alert.setHeaderText(null);
	    alert.setContentText("L'esecuzione dell'algoritmo è FINITO");

	    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	    stage.showAndWait();
	    
	}
}
