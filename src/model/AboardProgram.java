package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Represents an abroad program.
 *
 * @author Nguyen Truong Tho
 */
public class AboardProgram implements Serializable {

    private String id;
    private String name;
    private String time;
    private Date fromRegistrationDate;
    private Date endRegistrationDate;
    private int days;
    private List<String> location;
    private double cost;
    private String content;

    /**
     * Constructor of AboardProgram.
     *
     * @param id the ID of the abroad program
     * @param name the name of the abroad program
     * @param time the time of the abroad program
     * @param fromRegistrationDate the start date for program registration
     * @param endRegistrationDate the end date for program registration
     * @param days the number of days for the program
     * @param location the list of program locations
     * @param cost the cost of the program
     * @param content the content/description of the program
     */
    public AboardProgram(String id, String name, String time, Date fromRegistrationDate, Date endRegistrationDate, int days, List<String> location, double cost, String content) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.fromRegistrationDate = fromRegistrationDate;
        this.endRegistrationDate = endRegistrationDate;
        this.days = days;
        this.location = location;
        this.cost = cost;
        this.content = content;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of time
     *
     * @return the value of time
     */
    public String getTime() {
        return time;
    }

    /**
     * Set the value of time
     *
     * @param time new value of time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Get the value of fromRegistrationDate
     *
     * @return the value of fromRegistrationDate
     */
    public Date getFromRegistrationDate() {
        return fromRegistrationDate;
    }

    /**
     * Set the value of fromRegistrationDate
     *
     * @param fromRegistrationDate new value of fromRegistrationDate
     */
    public void setFromRegistrationDate(Date fromRegistrationDate) {
        this.fromRegistrationDate = fromRegistrationDate;
    }

    /**
     * Get the value of endRegistrationDate
     *
     * @return the value of endRegistrationDate
     */
    public Date getEndRegistrationDate() {
        return endRegistrationDate;
    }

    /**
     * Set the value of endRegistrationDate
     *
     * @param endRegistrationDate new value of endRegistrationDate
     */
    public void setEndRegistrationDate(Date endRegistrationDate) {
        this.endRegistrationDate = endRegistrationDate;
    }

    /**
     * Get the value of days
     *
     * @return the value of days
     */
    public int getDays() {
        return days;
    }

    /**
     * Set the value of days
     *
     * @param days new value of days
     */
    public void setDays(int days) {
        this.days = days;
    }

    /**
     * Get the value of location
     *
     * @return the value of location
     */
    public List<String> getLocation() {
        return location;
    }

    /**
     * Set the value of location
     *
     * @param location new value of location
     */
    public void setLocation(List<String> location) {
        this.location = location;
    }

    /**
     * Get the value of cost
     *
     * @return the value of cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Set the value of cost
     *
     * @param cost new value of cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Get the value of content
     *
     * @return the value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the value of content
     *
     * @param content new value of content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns a string representation of the AboardProgram object.
     *
     * @return a string representation of the AboardProgram object
     */
    @Override
    public String toString() {
        return "AboardProgram{" + "id=" + id + ", name=" + name + ", time=" + time + ", fromRegistrationDate=" + fromRegistrationDate + ", endRegistrationDate=" + endRegistrationDate + ", days=" + days + ", location=" + location + ", cost=" + cost + ", content=" + content + '}';
    }
}
