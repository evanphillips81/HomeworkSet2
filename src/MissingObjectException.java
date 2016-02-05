
/**
 *
 * @author Evan Phillips
 */
public class MissingObjectException extends Exception{
    
    @Override
    public String getMessage() {
        String eMessage = "You have not selected a person to remove.";
        return eMessage;
    }
}
