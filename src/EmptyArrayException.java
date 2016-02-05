/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author spill
 */
public class EmptyArrayException extends Exception{
    
    @Override
    public String getMessage() {
        String eMessage = "Nothing to save. Create some students before saving.";
        return eMessage;
    }
}
