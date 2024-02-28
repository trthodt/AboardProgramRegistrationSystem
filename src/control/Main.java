package control;

import bussiness.IcManagement;
import tools.Menu;

/**
 * The main class of the program.
 *
 * @author Nguyen Truong Tho
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String[] mainOptions = {"Manage aboard programs", "Manage students", "Register a program for a student", "Report", "Quit program"};
        String[] aboardOptions = {"Displays all aboard programs", "Add a new aboard program",
            "Edit information a program by id", "Search and display a program by id", "Back to main menu"};
        String[] studentOptions = {"Displays all students", "Add a new student", "Edit information a student by id", "Back to main menu"};
        String[] reportOptions = {"Show registration by student’s id", "Show students registered more than 2 programs", "Count students that registered the program", "Back to main menu"};
        IcManagement ic = new IcManagement();
        int choice;
        int aboardChoice;
        int studentChoice;
        int reportChoice;
        do {
            choice = Menu.getChoice(mainOptions);
            switch (choice) {
                case 1:
                    do {
                        aboardChoice = Menu.getChoice(aboardOptions);
                        switch (aboardChoice) {
                            case 1:
                                ic.displayProgram();
                                break;
                            case 2:
                                ic.addProgram();
                                break;
                            case 3:
                                ic.editProgram();
                                break;
                            case 4:
                                ic.searchProgram();
                                break;
                        }
                    } while (aboardChoice != 5);

                    break;
                case 2:
                    do {
                        studentChoice = Menu.getChoice(studentOptions);
                        switch (studentChoice) {
                            case 1:
                                ic.displayStudent();
                                break;
                            case 2:
                                ic.addStudent();
                                break;
                            case 3:
                                ic.editStudent();
                                break;
                        }
                    } while (studentChoice != 4);
                    break;
                case 3:
                    ic.addRegistration();
                    break;
                case 4:
                    do {
                        reportChoice = Menu.getChoice(reportOptions);
                        switch (reportChoice) {
                            case 1:
                                ic.printResgistration();
                                break;
                            case 2:
                                ic.printStudent2Program();
                                break;
                            case 3:
                                ic.countStudent();
                                break;
                        }
                    } while (reportChoice != 4);

                    break;
                case 5:
                    ic.quitProgram();
                    break;
            }
        } while (choice >= 1 && choice <= 5);
    }
}
