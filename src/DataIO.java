
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Evan Phillips
 */
public class DataIO implements Serializable {

    Student newStudent;
    int id;
    String firstName;
    String lastName;
    String phone;
    String address;
    String major;
    double gpa;
    StudentRecords sr;

    public DataIO() {
    }

    public StudentRecords getFromText(String fileName) throws IOException {
        sr = new StudentRecords(50);
        String[] data = new String[7];
        String line = null;

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                data = line.split(",");
                id = Integer.parseInt(data[0]);
                firstName = data[1];
                lastName = data[2];
                phone = data[3];
                address = data[4];
                major = data[5];
                gpa = Double.parseDouble(data[6]);

                newStudent = new Student(id, firstName, lastName, phone, address, major, gpa);
                sr.addStudentRecord(newStudent);
            }
            AlertWindow.show(GUI.G_TITLE, "The text file was loaded correctly");
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sr;
    }

    public StudentRecords getFromBin(String fileName) {

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream("file.dat");
            ois = new ObjectInputStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            sr = (StudentRecords) ois.readObject();
            AlertWindow.show(GUI.G_TITLE, "The binary file was loaded correctly");
            return sr;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveToBin(String fileName, StudentRecords sr) {

        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(sr);
            oos.close();
            AlertWindow.show(GUI.G_TITLE, "The file was created successfully");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
