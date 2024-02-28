package bussiness;

import java.util.ArrayList;
import tools.InputFormatter;
import java.util.HashMap;
import java.util.List;
import model.Student;
import tools.FileIO;

/**
 * Represents a management class for students.
 *
 * @author Nguyen Truong Tho
 */
public class StudentManagement extends HashMap<String, Student> {

    private final String studentIdRegex = "^(([Ss][EeBb])|([Gg][Dd])|([Mm][Cc]))([0-9]{6,6})$";
    private final String studentExc = "Student's ID must be valid (include major and 6 digit)!";
    private final String studentsFile = "src\\file\\students.dat";
    private boolean isSaved = true;

    /**
     * Constructs a new instance of StudentManagement and loads students from
     * file.
     */
    public StudentManagement() {
        List<Student> students = FileIO.loadData(studentsFile);
        for (Student st : students) {
            this.put(st.getId(), st);
        }
    }

    /**
     * Gets the value indicating whether the programs have been saved.
     *
     * @return true if the programs have been saved; otherwise, false.
     */
    public boolean getIsSaved() {
        return isSaved;
    }

    /**
     * Sets the value indicating whether the programs have been saved.
     *
     * @param isSaved true if the programs have been saved; otherwise, false.
     */
    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

    /**
     * Displays the list of students.
     */
    public void displayStudent() {
        if (!this.isEmpty()) {
            showStudentTable(new ArrayList<>(this.values()));
        } else {
            System.out.println("There are no students!");
        }
    }

    /**
     * Adds a new student to the student management system.
     */
    public void addStudent() {
        String id = InputFormatter.getString("Enter Student's Id: ", studentExc, studentIdRegex).toUpperCase();
        if (!this.containsKey(id)) {
            String name = InputFormatter.getStringNotNull("Enter Student's Name: ");
            String major = id.substring(0, 2);
            String email = InputFormatter.getMail("Enter Email: ", "@fpt.edu.vn");
            String phone = InputFormatter.getPhone("Enter Phone: ");
            String passport = InputFormatter.getStringNotNull("Enter Passport: ");
            String address = InputFormatter.getStringNotNull("Enter Address: ");
            this.put(id, new Student(id, name, major, email, phone, passport, address));
            System.out.println(name + " has been added!");
            setIsSaved(false);
        } else {
            System.out.println("Student's ID adready exist!");
        }
    }

    /**
     * Updates the information of an existing student.
     */
    public void updateStudent() {
        String id = InputFormatter.getString("Enter Student's Id: ", "Student's ID must be in ", studentIdRegex).toUpperCase();
        if (this.containsKey(id)) {
            String name = InputFormatter.getString("Enter Student's Name: ");
            String major = InputFormatter.getString("Enter Major: ");
            String email = InputFormatter.getString("Enter Email: ");
            String phone = InputFormatter.getString("Enter Phone: ");
            String passport = InputFormatter.getString("Enter Passport: ");
            String address = InputFormatter.getString("Enter Address: ");
            try {
                if (!name.isEmpty() && !name.matches("^[a-zA-Z ]+$")) {
                    throw new Exception("Name must only include letters!");
                }
                if (!major.isEmpty() && !major.matches("^([Ss][EeBb])|([Gg][Dd])|([Mm][Cc])$")) {
                    throw new Exception("Major only accept as: SE,SB,GD,MC");
                }
                if (!email.isEmpty() && !email.matches("^([0-9a-zA-Z.]+)([@])([0-9a-zA-Z.]+)$")) {
                    throw new Exception("Email must only include letter, number and end with domain \"@fpt.edu.vn\"");
                }
                if (!phone.isEmpty() && !phone.matches("^0[1-9]{1,1}[0-9]{8,8}$")) {
                    throw new Exception("Phone must be valid!");
                }
            } catch (Exception e) {
                System.out.println("Unvalid input! " + e.getMessage());
                System.out.println("Update failure!");
            } finally {
                if (!name.isEmpty()) {
                    this.get(id).setName(name);
                }
                if (!major.isEmpty()) {
                    this.get(id).setMajor(major);
                }
                if (!email.isEmpty()) {
                    this.get(id).setEmail(email);
                }
                if (!phone.isEmpty()) {
                    this.get(id).setPhone(phone);
                }
                if (!passport.isEmpty()) {
                    this.get(id).setPassport(passport);
                }
                if (!address.isEmpty()) {
                    this.get(id).setEmail(email);
                }
                System.out.println("Update successful!");
                setIsSaved(false);
            }

        } else {
            System.out.println("The student does not exist!");
        }
    }

    /**
     * Displays a table of students.
     *
     * @param list The list of students to display.
     */
    public void showStudentTable(List<Student> list) {
        System.out.println("_____________________________________________________________________________________________________________________");
        System.out.println("|    ID    |         NAME         | MAJOR |            EMAIL             |    PHONE     |  PASSPORT |    ADDRESS    |");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        for (Student st : list) {
            System.out.println(st);
        }
        System.out.println("_____________________________________________________________________________________________________________________");

    }

    /**
     * Saves the list of students to a file.
     */
    public void saveStudents() {
        FileIO.saveData(new ArrayList(this.values()), studentsFile);
        setIsSaved(true);
    }

}
