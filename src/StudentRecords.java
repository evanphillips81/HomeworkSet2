
import java.io.Serializable;


/**
 *
 * @author Evan Phillips
 *
 */
public class StudentRecords implements Serializable{

    private Student[] sr;
    private int nElems;

    public StudentRecords(int size) {
        sr = new Student[size];
        nElems = 0;
    }
    
    public Student getStudent(int i) {
        return sr[i];
    }
    
    public int getNelems() {
        return nElems;
    }

    public Student[] getStudents() {
        return sr;
    }

    public void setStudents(Student[] newSr) {
        this.sr = newSr;
    }

    public void addStudentRecord(Student newStudent) {
        sr[nElems] = newStudent;
        nElems++;
    }

    public void removeStudentRecord(int id) {
        int j = 0;

        for (j = 0; j < nElems; j++) {
            if (sr[j].getId() == id) {
                break;
            }
        }

        if (j == nElems) {
            System.out.println("not found");
        } else {
            for (int k = j; k < nElems; k++) {
                sr[k] = sr[k + 1];
            }

            nElems--;
        }
    }
    
    public boolean foundStudent(int id) {
        
        for (int i = 0; i < nElems; i++) {
            if (sr[i].getId() == id) {
                return true;
            }
        }
        
        return false;
    }
    
    public Student searchStudentId(int id) {
                
        for (int i = 0; i < nElems; i++) {
            if (sr[i].getId() == id) {
                return sr[i];
            }       
        }
        
        AlertWindow.show(GUI.E_TITLE,"Could not find any students with that ID.");

        return null;
    }
}