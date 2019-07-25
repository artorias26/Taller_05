package config;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


public class AlertHelper {
    
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    
    public static void closeWindowAlert(Stage viewLogin) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Avertencia");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro que quieres salir?");
        
        Optional<ButtonType> option = alert.showAndWait();
        
        if (option.get() == ButtonType.OK) {
            viewLogin.close();
        }
    }
}
