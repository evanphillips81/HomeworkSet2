
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
public class NullFieldException extends Exception {
           
    @Override
    public String getMessage() {
        String eMessage = "You left one or more of the fields blank.";
        return eMessage;
    }
}