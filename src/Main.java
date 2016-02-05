
import java.io.Serializable;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 *
 * @author Evan Phillips
 */
public class Main extends Application {
    
    public static void main(String[] args) {
        
        launch();
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        GUI gui = new GUI(stage);
        gui.runMainScene();
    }
}

