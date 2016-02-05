
import java.io.Serializable;


/**
 *
 * @author Evan Phillips
 */
public class Student extends Person implements Serializable {
    
    private String phone;
    private String address;
    private String major;
    private double GPA;

    public Student(int id, String firstName, String lastName, String phone, 
                    String address, String major, double GPA) {
        super(id, firstName, lastName);
        this.phone = phone;
        this.address = address;
        this.major = major;
        this.GPA = GPA;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }
    
    @Override
    public String toString() {
        String str = ("\n" + super.toString() + "\nPhone number: " + phone 
                        + "\nAddress: " + address + "\nMajor: " + major 
                        + "\nGPa: " + GPA);
        return str;
    }
    
}
