package bussiness;

import tools.InputFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import model.AboardProgram;
import tools.FileIO;

/**
 * Represents a management class for AboardProgram objects.
 *
 * @author Nguyen Truong Tho
 */
public class AboardProgramManagement extends HashMap<String, AboardProgram> {

    private List<String> timeList = Arrays.asList("January", "March", "May", "July", "September", "November");
    private final String programIdRegex = "^([a-zA-Z])[0-9]{3,4}$";
    private final String programExc = "Aboard Program's ID must be valid (include 1 letter and 3-4 digit!";
    private boolean isSaved = true;
    private final String programsFile = "src\\file\\programs.dat";

    /**
     * Initializes a new instance of the AboardProgramManagement class. Loads
     * existing programs from data file.
     */
    public AboardProgramManagement() {
        List<AboardProgram> programs = FileIO.loadData(programsFile);
        for (AboardProgram ap : programs) {
            this.put(ap.getId(), ap);
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
     * Prints the details of an AboardProgram.
     *
     * @param ap The AboardProgram object to print.
     */
    private void printAboard(AboardProgram ap) {
        System.out.println("0. ID and Name: " + ap.getId() + "-" + ap.getName());
        System.out.println("1. Time: " + ap.getTime());
        System.out.println("2. Day: " + ap.getDays() + " days");
        System.out.println("3. Location:");
        showLocation(ap);
        System.out.printf("4. Cost: %.0f$\n", ap.getCost());
        System.out.println("5. Content: " + ap.getContent());
    }

    /**
     * Displays the AboardProgram objects in the management class.
     */
    public void displayProgram() {
        if (!this.isEmpty()) {
            System.out.println("-------------------------------------------------------------------");
            for (AboardProgram ap : this.values()) {
                printAboard(ap);
                System.out.println("-------------------------------------------------------------------");
            }
        } else {
            System.out.println("There are no Aboard Program have been saved yet!");
        }
    }

    /**
     * Adds a new AboardProgram to the management class.
     *
     * @throws ParseException if there is an error parsing the input.
     * @throws Exception if there is an error during program addition.
     */
    public void addProgram() throws ParseException, Exception {
        String id = InputFormatter.getString("Enter Program's ID: ", programExc, programIdRegex);
        if (!this.containsKey(id)) {
            String name = InputFormatter.getStringNotNull("Enter Program's Name: ");
            String time = InputFormatter.getStringInList("Enter time: ", timeList);
            Date fromRegistrationDate = InputFormatter.getDate("Enter From Registration Date: ");
            Date endRegistrationDate = InputFormatter.getDateAfter("Enter End Registration Date: ", fromRegistrationDate);
            int days = InputFormatter.getInt("Enter days: ", "Days must be from 30 to 40 days!", 30, 40);
            System.out.println("Enter location (enter to stop inputing):");
            List<String> location = InputFormatter.getStringList("Location: ");
            while (location.isEmpty()) {
                location = InputFormatter.getStringList("Enter location: ");
            }
            double cost = InputFormatter.getDouble("Enter Cost: ", "Cost must be greater than 0.", 0, Double.MAX_VALUE);
            String content = InputFormatter.getStringNotNull("Enter Content: ");
            this.put(id, new AboardProgram(id, name, time, fromRegistrationDate, endRegistrationDate, days, location, cost, content));
            System.out.println(name + " has been added!");
            setIsSaved(false);
        } else {
            System.out.println("Program's ID already exist!");
        }
    }

    /**
     * Updates an existing AboardProgram in the management class.
     *
     * @throws Exception if there is an error during program update.
     */
    public void updateProgram() throws Exception {
        String id = InputFormatter.getString("Enter Program's ID: ", programExc, programIdRegex);
        if (this.containsKey(id)) {
            String name = InputFormatter.getString("Enter Program's Name: ");
            String time = InputFormatter.getString("Enter time: ");
            String fromRegistrationDate = InputFormatter.getString("Enter From Registration Date: ");
            String endRegistrationDate = InputFormatter.getString("Enter End Registration Date: ");
            String days = InputFormatter.getString("Enter days: ");
            List<String> location = InputFormatter.getStringList("Enter location: ");
            String cost = InputFormatter.getString("Enter Cost: ");
            String content = InputFormatter.getString("Enter Content: ");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                if (!name.isEmpty() && !name.matches("^[a-zA-Z0-9 ]+$")) {
                    throw new Exception("Name must only include letters and numbers!");
                }
                if (!time.isEmpty() && !timeList.contains(time.substring(0, 1) + time.substring(1))) {
                    throw new Exception("Only accept as: January, March, May, July, September, November!");
                }
                if (!fromRegistrationDate.isEmpty() && !InputFormatter.isValidDate(fromRegistrationDate)) {
                    throw new Exception("Wrong date format!");
                }
                if (!endRegistrationDate.isEmpty() && !InputFormatter.isValidDate(endRegistrationDate)) {
                    throw new Exception("Wrong date format!");
                }
                if (!days.isEmpty() && (Integer.parseInt(days) < 30 || Integer.parseInt(days) > 40)) {
                    throw new Exception("Days must from 30 to 40 days!");
                }
                if (!cost.isEmpty() && Double.parseDouble(cost) < 0) {
                    throw new Exception("Cost must greater than 0!");
                }
            } catch (Exception e) {
                System.out.println("Update failure! " + e.getMessage());
            } finally {
                if (!name.isEmpty()) {
                    this.get(id).setName(name);
                }
                if (!time.isEmpty()) {
                    this.get(id).setTime(time);
                }
                if (!fromRegistrationDate.isEmpty()) {
                    this.get(id).setFromRegistrationDate(sdf.parse(fromRegistrationDate));
                }
                if (!endRegistrationDate.isEmpty()) {
                    this.get(id).setEndRegistrationDate(sdf.parse(endRegistrationDate));
                }
                if (!days.isEmpty()) {
                    this.get(id).setDays(Integer.parseInt(days));
                }
                if (!location.isEmpty()) {
                    this.get(id).setLocation(location);
                }
                if (!cost.isEmpty()) {
                    this.get(id).setCost(Double.parseDouble(cost));
                }
                if (!content.isEmpty()) {
                    this.get(id).setContent(content);
                }
                System.out.println("Update successful!");
                setIsSaved(false);
            }
        } else {
            System.out.println("Program does not exist!");
        }
    }

    /**
     * Searches for an AboardProgram by ID in the management class.
     */
    public void searchProgram() {
        String id = InputFormatter.getString("Enter Program's ID: ", programExc, programIdRegex);
        if (this.containsKey(id)) {
            printAboard(this.get(id));
        } else {
            System.out.println("The Program does not exist!");
        }
    }

    /**
     * Displays the locations of an AboardProgram.
     *
     * @param ap The AboardProgram object.
     */
    public void showLocation(AboardProgram ap) {
        int i = 1;
        for (String str : ap.getLocation()) {
            System.out.println("\t" + i++ + ". " + str);
        }
    }

    /**
     * Saves the AboardPrograms to a data file.
     */
    public void saveProgram() {
        FileIO.saveData(new ArrayList(this.values()), programsFile);
        setIsSaved(true);
    }
}
