package bussiness;

import tools.InputFormatter;

/**
 * The IC management class handles operations related to managing students,
 * aboard program and register a program for a student
 *
 * @author Nguyen Truong Tho
 */
public class IcManagement {

    StudentManagement students = new StudentManagement();
    AboardProgramManagement programs = new AboardProgramManagement();
    RegistrationFormManagement registrations = new RegistrationFormManagement();

    /**
     * Displays all aboard programs.
     */
    public void displayProgram() {
        programs.displayProgram();
    }

    /**
     * Adds a new aboard program.
     *
     * @throws Exception if an error occurs during program addition.
     */
    public void addProgram() throws Exception {
        programs.addProgram();
    }

    /**
     * Edits an existing aboard program.
     *
     * @throws Exception if an error occurs during program editing.
     */
    public void editProgram() throws Exception {
        programs.updateProgram();
    }

    /**
     * Searches and displays a program by its ID.
     */
    public void searchProgram() {
        programs.searchProgram();
    }

    /**
     * Displays all students.
     */
    public void displayStudent() {
        students.displayStudent();
    }

    /**
     * Adds a new student.
     */
    public void addStudent() {
        students.addStudent();
    }

    /**
     * Edits an existing student.
     */
    public void editStudent() {
        students.updateStudent();
    }

    /**
     * Adds a registration form for a student and a program.
     *
     * @throws Exception if an error occurs during registration form addition.
     */
    public void addRegistration() throws Exception {
        if (!students.isEmpty()) {
            if (!programs.isEmpty()) {
                registrations.addRegistration(students, programs);
            } else {
                System.out.println("There are no programs have been saved yet!");
            }
        } else {
            System.out.println("There are no students have been saved yet!");
        }
    }

    /**
     * Prints the registration details.
     */
    public void printResgistration() {
        registrations.printRes(students);
    }

    /**
     * Prints the students who are registered for more than 2 programs.
     */
    public void printStudent2Program() {
        registrations.printStudent2Program(students);
    }

    /**
     * Counts the number of students registered for a program.
     */
    public void countStudent() {
        registrations.countStudent(programs);
    }

    /**
     * Quits the program execution.
     */
    public void quitProgram() {
        if (InputFormatter.getBoolean("Do you want to quit? (Y/N): ")) {
            if (!students.getIsSaved() || !programs.getIsSaved()) {
                if (InputFormatter.getBoolean("Data has been changed! Do you to save before exit? (Y/N): ")) {
                    programs.saveProgram();
                    students.saveStudents();
                }
            }
            System.out.println("Goodbye, have a nice day!");
            System.exit(0);
        } else {
            System.out.println("Continue...");
        }
    }
}
