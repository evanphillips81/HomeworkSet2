
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Evan Phillips
 */
public class GUI {

    static final double W = 625;
    static final double H = 400;
    static final double SP_ = 10;
    static final int SIZE = 500;
    static final String G_TITLE = "Success";
    static final String E_TITLE = "Something went wrong";
    static final String TXTFILE = "studentRecords.txt";
    static final String BINFILE = "studentRecords.bin";

    private StudentRecords sr;
    private Stage stage;
    private BorderPane layout;
    private VBox topPane;
    private HBox searchPane;
    private HBox bottomPane;
    private GridPane centerPane;
    private MenuBar menu;
    private Menu fileM;
    private Menu editM;
    private MenuItem openFromText;
    private MenuItem openFromBin;
    private MenuItem saveToBin;
    private MenuItem addMI;
    private MenuItem removeMI;
    private MenuItem closeMI;
    private TextField idTf;
    private TextField firstNameTf;
    private TextField lastNameTf;
    private TextField phoneTf;
    private TextField addressTf;
    private TextField majorTf;
    private TextField gpaTf;
    private Label idL;
    private Label firstNameL;
    private Label lastNameL;
    private Label phoneL;
    private Label addressL;
    private Label majorL;
    private Label gpaL;
    private Button addB;
    private Button removeB;
    private Button searchB;
    private Button closeB;
    private Image addImg;
    private Image removeImg;
    private Image searchImg;
    private Image closeImg;

    public GUI(Stage stage) {
        this.stage = stage;
        sr = new StudentRecords(SIZE);
        topPane = new VBox();
        searchPane = new HBox();
        bottomPane = new HBox();
        centerPane = new GridPane();
        idTf = new TextField();
        firstNameTf = new TextField();
        lastNameTf = new TextField();
        phoneTf = new TextField();
        addressTf = new TextField();
        majorTf = new TextField();
        gpaTf = new TextField();
        menu = new MenuBar();
        fileM = new Menu("File");
        editM = new Menu("Edit");
        openFromText = new MenuItem("Open a text file");
        openFromBin = new MenuItem("Open a binary file");
        saveToBin = new MenuItem("Save");
        addMI = new MenuItem("Add");
        removeMI = new MenuItem("Remove");
        closeMI = new MenuItem("Close");
        addB = new Button();
        removeB = new Button();
        searchB = new Button();
        closeB = new Button();
        idL = new Label("ID");
        firstNameL = new Label("First name");
        lastNameL = new Label("Last name");
        phoneL = new Label("Phone number");
        addressL = new Label("Address");
        majorL = new Label("Major");
        gpaL = new Label("GPA");
        addImg = new Image(getClass().getResourceAsStream("add.png"));
        removeImg = new Image(getClass().getResourceAsStream("remove.png"));
        searchImg = new Image(getClass().getResourceAsStream("search.png"));
        closeImg = new Image(getClass().getResourceAsStream("exit.png"));
    }

    private VBox getTopPane() {
        searchB.setGraphic(new ImageView(searchImg));
        searchB.setTooltip(new Tooltip("Search for a student ID."));
        fileM.getItems().addAll(openFromText, openFromBin, new SeparatorMenuItem(), saveToBin, new SeparatorMenuItem(), closeMI);
        editM.getItems().addAll(addMI, removeMI);
        menu.getMenus().addAll(fileM, editM);
        searchPane.setAlignment(Pos.CENTER);
        searchPane.setSpacing(SP_);
        searchPane.setPadding(new Insets(SP_, SP_, SP_, SP_));
        searchPane.getChildren().addAll(idL, idTf, searchB);
        topPane.setAlignment(Pos.CENTER);
        topPane.setSpacing(SP_);
        topPane.getChildren().addAll(menu, searchPane);

        return topPane;
    }

    private GridPane getCenterPane() {
        centerPane.setAlignment(Pos.CENTER);
        centerPane.setPadding(new Insets(SP_, SP_, SP_, SP_));
        centerPane.setHgap(SP_);
        centerPane.setVgap(SP_);
        centerPane.add(firstNameL, 0, 1);
        centerPane.add(phoneL, 0, 2);
        centerPane.add(gpaL, 0, 3);
        centerPane.add(lastNameL, 2, 1);
        centerPane.add(addressL, 2, 2);
        centerPane.add(majorL, 2, 3);
        centerPane.add(firstNameTf, 1, 1);
        centerPane.add(phoneTf, 1, 2);
        centerPane.add(gpaTf, 1, 3);
        centerPane.add(lastNameTf, 3, 1);
        centerPane.add(addressTf, 3, 2);
        centerPane.add(majorTf, 3, 3);

        return centerPane;
    }

    private HBox getBottomPane() {
        addB.setGraphic(new ImageView(addImg));
        addB.setTooltip(new Tooltip("Add student to the database."));
        removeB.setGraphic(new ImageView(removeImg));
        removeB.setTooltip(new Tooltip("Remove student from the database."));
        closeB.setGraphic(new ImageView(closeImg));
        closeB.setTooltip(new Tooltip("Exit the database application."));
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setSpacing(SP_);
        bottomPane.setPadding(new Insets(SP_, SP_, SP_, SP_));
        bottomPane.getChildren().addAll(addB, removeB, closeB);

        return bottomPane;
    }

    private BorderPane getLayout() {
        layout = new BorderPane();
        layout.setTop(getTopPane());
        layout.setCenter(getCenterPane());
        layout.setBottom(getBottomPane());

        return layout;
    }

    public void runMainScene() {

        Scene scene = new Scene(getLayout(), W, H);
        scene.getStylesheets().add("superCool.css");
        stage.getIcons().add(new Image("user.png"));
        stage.setTitle("Student Records");
        stage.setScene(scene);
        stage.show();
        handleActions();
    }

    private void addNewStudent() {
        Student newStudent;

        int id;
        String firstName;
        String lastName;
        String phone;
        String address;
        String major;
        double gpa;

        try {
            if (!idTf.getText().trim().isEmpty() && !firstNameTf.getText().trim().isEmpty()
                    && !lastNameTf.getText().trim().isEmpty() && !phoneTf.getText().trim().isEmpty()
                    && !addressTf.getText().trim().isEmpty() && !majorTf.getText().trim().isEmpty()
                    && !gpaTf.getText().trim().isEmpty()) {

                id = Integer.parseInt(idTf.getText());
                firstName = firstNameTf.getText();
                lastName = lastNameTf.getText();
                phone = phoneTf.getText();
                address = addressTf.getText();
                major = majorTf.getText();
                gpa = Double.parseDouble(gpaTf.getText());

                try {
                    if (gpa >= 0.0 && gpa <= 4.0) {
                        newStudent = new Student(id, firstName, lastName, phone, address, major, gpa);
                        sr.addStudentRecord(newStudent);
                        AlertWindow.show(
                                G_TITLE, "The student record was added" + newStudent);
                        setFieldsBlank();

                    } else {
                        throw (new InvalidGPAException());
                    }
                } catch (InvalidGPAException e) {
                    AlertWindow.show(E_TITLE, e.getMessage());
                }

            } else {
                throw (new NullFieldException());
            }

        } catch (NullFieldException e) {
            AlertWindow.show(E_TITLE, e.getMessage());
        }
    }

    private void removeStudent() {
        try {
            if (!idTf.getText().trim().isEmpty() && !firstNameTf.getText().trim().isEmpty()
                    && !lastNameTf.getText().trim().isEmpty() && !phoneTf.getText().trim().isEmpty()
                    && !addressTf.getText().trim().isEmpty() && !majorTf.getText().trim().isEmpty()
                    && !gpaTf.getText().trim().isEmpty()) {
                if (sr.foundStudent(Integer.parseInt(idTf.getText()))) {
                    sr.removeStudentRecord(Integer.parseInt(idTf.getText()));
                    AlertWindow.show(G_TITLE, "The student was removed from the database");
                    setFieldsBlank();
                }
            } else {
                throw (new MissingObjectException());
            }
        } catch (MissingObjectException e) {
            AlertWindow.show(E_TITLE, e.getMessage());
        }
    }

    private void searchStudent() {
        Student s;
        try {
            if (!idTf.getText().trim().isEmpty()) {
                if (sr.foundStudent(Integer.parseInt(idTf.getText()))) {
                    s = sr.searchStudentId(Integer.parseInt(idTf.getText()));
                    setFields(s);   
                } else {
                    AlertWindow.show(E_TITLE, "Student not found");
                }
            } else {
                throw (new NullFieldException());
            }
        } catch (NullFieldException ex) {
            AlertWindow.show(E_TITLE, ex.getMessage());
        }
    }

    private void openTextFile() {
        DataIO input = new DataIO();
        try {
            sr = input.getFromText(TXTFILE);
        } catch (IOException ex) {
            
        }
    }

    private void openFromBin() {
        DataIO input = new DataIO();
        sr = input.getFromBin(BINFILE);
    }

    private void saveToBin() {
        try {
            if (sr.getNelems() != 0) {
                DataIO output = new DataIO();
                output.saveToBin(BINFILE, sr);
            } else {
                throw (new EmptyArrayException());
            }
        } catch (EmptyArrayException e) {
            AlertWindow.show(E_TITLE,e.getMessage());
        }
    }

    private void setFieldsBlank() {
        idTf.clear();
        firstNameTf.clear();
        lastNameTf.clear();
        phoneTf.clear();
        addressTf.clear();
        majorTf.clear();
        gpaTf.clear();
    }

    private void setFields(Student s) {
        idTf.setText("" + s.getId());
        firstNameTf.setText(s.getFirstName());
        lastNameTf.setText(s.getLastName());
        phoneTf.setText(s.getPhone());
        addressTf.setText(s.getAddress());
        majorTf.setText(s.getMajor());
        gpaTf.setText("" + s.getGPA());
    }
    
    private void handleActions() {

        addB.setOnAction(e -> {
            addNewStudent();
        });

        addMI.setOnAction(e -> {
            addNewStudent();
        });

        removeB.setOnAction(e -> {
            removeStudent();
        });

        removeMI.setOnAction(e -> {
            removeStudent();
        });

        searchB.setOnAction(e -> {
            searchStudent();
        
        });

        openFromText.setOnAction(e -> {
            openTextFile();
        });

        openFromBin.setOnAction(e -> {
            openFromBin();
        });

        saveToBin.setOnAction(e -> {
            saveToBin();
        });

        closeB.setOnAction(e -> {
            stage.close();
        });

        closeMI.setOnAction(e -> {
            stage.close();
        });
    }
}
