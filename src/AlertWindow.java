
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 *
 * @author Evan Phillips
 */
public class AlertWindow {
    
    Stage stage;
    String title;
    String message;  
    
    static void show(String title, String message) {
        Stage prompt = new Stage();
        prompt.initModality(Modality.APPLICATION_MODAL);
        prompt.setTitle(title);
        
        Label messageL = new Label(message);
        Button okB = new Button("Ok");
        okB.setStyle("-fx-background-color: #7CAFC2");
        
        okB.setOnAction(e -> {
            prompt.close();
        });
        
        VBox vb = new VBox();
        vb.setSpacing(GUI.SP_);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(messageL,okB);
        
        Scene scene = new Scene(vb,375,250);
        scene.getStylesheets().add("superCool.css");
        prompt.setScene(scene);
        prompt.show();
    } 
    
}
