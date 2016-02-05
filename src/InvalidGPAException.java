
/**
 *
 * @author Evan Phillips
 */
public class InvalidGPAException extends Exception {
    
    @Override
    public String getMessage() {
        String eMessage = "Please enter a valid GPA. (0.0 < GPA < 4.0)";
        return eMessage;
    }
}
