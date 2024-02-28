package model;

import java.io.Serializable;

/**
 * Represents a student.
 *
 * @author Nguyen Truong Tho
 */
public class Student implements Serializable {

    private String id;
    private String name;
    private String major;
    private String email;
    private String phone;
    private String passport;
    private String address;

    /**
     * Constructor of Student.
     *
     * @param id the ID of the student
     * @param name the name of the student
     * @param major the major of the student
     * @param email the email address of the student
     * @param phone the phone number of the student
     * @param passport the passport number of the student
     * @param address the address of the student
     */
    public Student(String id, String name, String major, String email, String phone, String passport, String address) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.email = email;
        this.phone = phone;
        this.passport = passport;
        this.address = address;
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
     * Get the value of major
     *
     * @return the value of major
     */
    public String getMajor() {
        return major;
    }

    /**
     * Set the value of major
     *
     * @param major new value of major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of phone
     *
     * @return the value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the value of phone
     *
     * @param phone new value of phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get the value of passport
     *
     * @return the value of passport
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Set the value of passport
     *
     * @param passport new value of passport
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns a string representation of the Student object.
     *
     * @return a string representation of the Student object
     */
    @Override
    public String toString() {
        return String.format("| %-8s | %-20s | %-5s | %-28s | %-12s | %-9s | %-13s |", id, name, major, email, phone, passport, address);
    }

}
