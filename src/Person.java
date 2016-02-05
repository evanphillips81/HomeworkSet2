
import java.io.Serializable;



/**
 *
 * @author Evan Phillips
 * 
 */
public abstract class Person implements Serializable{
    
    private int id;
    private String firstName;
    private String lastName;

    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;  
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        String str = ("\n ID: " + id + "\nFirst Name: " 
                        + firstName + "\nLast Name: " + lastName);
        return str;
    }
    
}
