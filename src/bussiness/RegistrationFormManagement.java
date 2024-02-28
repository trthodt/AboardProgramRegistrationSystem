package bussiness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import tools.InputFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import model.AboardProgram;
import model.Student;

/**
 * Represents a management class for registration forms.
 *
 * @author Nguyen Truong Tho
 */
public class RegistrationFormManagement {

    private final String studentIdRegex = "^(([Ss][EeBb])|([Gg][Dd])|([Mm][Cc]))([0-9]{6,6})$";
    private final String studentExc = "Student's ID must be valid (include major and 6 digit)!";
    private final String programIdRegex = "^([a-zA-Z])[0-9]{3,4}$";
    private final String programExc = "Aboard Program's ID must be valid (include 1 letter and 3-4 digit!";
    private final String dirFile = "src\\RegistrationForm\\";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");

    /**
     * Adds a new registration form for a student.
     *
     * @param students The StudentManagement object.
     * @param programs The AboardProgramManagement object.
     * @throws ParseException if there is an error parsing the input.
     * @throws Exception if there is an error during registration form addition.
     */
    public void addRegistration(StudentManagement students, AboardProgramManagement programs) throws ParseException, Exception {
        String studentId = InputFormatter.getString("Enter Student's Id: ", studentExc, studentIdRegex).toUpperCase();
        if (students.containsKey(studentId)) {
            String programId = InputFormatter.getString("Enter Program's ID: ", programExc, programIdRegex).toUpperCase();
            if (programs.containsKey(programId)) {
                Date registrationDate = InputFormatter.getDateAfter("Enter Registration Date: ", programs.get(programId).getFromRegistrationDate());
                while (registrationDate.compareTo(programs.get(programId).getEndRegistrationDate()) > 0) {
                    registrationDate = registrationDate = InputFormatter.getDateAfter("Enter Registration Date: ", programs.get(programId).getFromRegistrationDate());
                }
                String parentMail = InputFormatter.getMail("Enter Parent's Mail: ", "@gmail.com");
                String parentPhone = InputFormatter.getPhone("Enter Parent's Phone: ");
                System.out.println("Choose location: ");
                List<String> locationList = programs.get(programId).getLocation();
                programs.showLocation(programs.get(programId));
                int choice = InputFormatter.getInt("Enter your choice: ", "Your choice must from 1 to " + locationList.size(), 1, locationList.size());
                String location = locationList.get(choice - 1);
                Student student = students.get(studentId);
                AboardProgram program = programs.get(programId);
                saveForm(student, program, registrationDate, parentMail, parentPhone, location);
            } else {
                System.out.println("The program does not exist!");
            }
        } else {
            System.out.println("The student does not exist!");
        }
    }

    /**
     * Saves the registration form to a file.
     *
     * @param student The Student object.
     * @param program The AboardProgram object.
     * @param registrationDate The registration date.
     * @param parentMail The parent's email.
     * @param parentPhone The parent's phone number.
     * @param location The chosen location.
     * @throws IOException if there is an error during file saving.
     */
    public void saveForm(Student student, AboardProgram program, Date registrationDate, String parentMail, String parentPhone, String location) throws IOException {
        String fileName = student.getId() + "_" + program.getId() + ".doc";
        try {
            File f = new File(dirFile + fileName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("\t\tAboard Program Registration Form");
            pw.println();
            pw.println("Information Student:");
            pw.println();
            pw.printf("%-25s%s\n", "Student's id: " + student.getId(), "Student name: " + student.getName());
            pw.println();
            pw.printf("%-25s%s   %s   %s\n", "Major: " + student.getMajor(), "Email: " + student.getEmail(), "Phone: " + student.getPhone(), "Passport: " + student.getPassport());
            pw.println();
            pw.printf("%-25s%s   %s\n", "Address: " + student.getAddress(), "Email of the parents: " + parentMail, "Phone of the parents: " + parentPhone);
            pw.println();
            pw.println("Information of the aboard program:");
            pw.println();
            pw.printf("%-25s%s\n", "Program's id: " + program.getId(), "Program's name: " + program.getName());
            pw.println();
            pw.printf("%-25s%s%d   %s   %s%.0f$\n", "Time: " + program.getTime(), "Days: ", program.getDays(), "Location: " + location, "Cost: ", program.getCost());
            pw.println();
            pw.println("Information of the registration:");
            pw.println();
            pw.println("Registration date: " + sdf.format(registrationDate));
            fw.close();
            pw.close();
            System.out.println(fileName + " has been saved!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prints the registration form for a specific student.
     *
     * @param students The StudentManagement object.
     */
    public void printRes(StudentManagement students) {
        try {
            File dir = new File(dirFile);
            List<File> fList = Arrays.asList(dir.listFiles());
            if (!fList.isEmpty()) {
                String studentId = InputFormatter.getString("Enter Student's Id: ", studentExc, studentIdRegex).toUpperCase();
                if (students.containsKey(studentId)) {
                    int count = 0;
                    for (File f : fList) {
                        if (f.getName().startsWith(studentId)) {
                            BufferedReader br = new BufferedReader(new FileReader(f));
                            String line;
                            System.out.println("---------------------------------------------------------------------------------------------------");

                            while ((line = br.readLine()) != null) {
                                System.out.println(line);
                            }
                            System.out.println("---------------------------------------------------------------------------------------------------");
                            count++;
                        }
                    }
                    System.out.println("Number of registration: " + count);

                } else {
                    System.out.println("The student does not exist!");
                }
            } else {
                System.out.println("No forms have been saved yet!");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prints the students who have registered for more than 2 programs.
     *
     * @param students The StudentManagement object.
     */
    public void printStudent2Program(StudentManagement students) {
        File dir = new File(dirFile);
        List<File> fList = Arrays.asList(dir.listFiles());
        List<Student> sList = new ArrayList<>();
        if (!fList.isEmpty()) {
            for (String id : students.keySet()) {
                int count = 0;
                for (File f : fList) {
                    if (f.getName().startsWith(id)) {
                        count++;
                    }
                }
                if (count > 2) {
                    sList.add(students.get(id));
                }
            }
        } else {
            System.out.println("No forms have been saved yet!");
        }
        if (!sList.isEmpty()) {
            students.showStudentTable(sList);
        } else {
            System.out.println("There are no students that registered more than 2 program!");
        }
    }

    /**
     * Counts the number of students who have registered for a specific program.
     *
     * @param programs The AboardProgramManagement object.
     */
    public void countStudent(AboardProgramManagement programs) {
        String programId = InputFormatter.getString("Enter Program's ID: ", programExc, programIdRegex).toUpperCase();
        if (programs.containsKey(programId)) {
            File dir = new File(dirFile);
            List<File> fList = Arrays.asList(dir.listFiles());
            if (!fList.isEmpty()) {
                int count = 0;
                for (File f : fList) {
                    if (f.getName().contains(programId + ".")) {
                        count++;
                    }
                }
                System.out.println("Number of students that registered the program: " + count);
            } else {
                System.out.println("No forms of this have been saved yet!");
            }
        } else {
            System.out.println("Program does not exist!");
        }
    }

}
