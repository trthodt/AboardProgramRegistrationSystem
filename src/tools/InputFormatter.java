package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Provides methods for validating user input.
 *
 * @author Nguyen Truong Tho
 */
public class InputFormatter {

    private static final Scanner input = new Scanner(System.in);
    private static String dateFormat = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)[0-9]{2,2}$";
    private static String mailRegex = "^([0-9a-zA-Z.]+)([@])([0-9a-zA-Z.]+)$";
    private static String phoneRegex = "^0[1-9]{1,1}[0-9]{8,8}$";

    /**
     * Method for inputting an integer number within a specified range.
     *
     * @param min The minimum value of the number.
     * @param max The maximum value of the number.
     * @return An integer number within the range entered by the user.
     */
    public static int getInt(int min, int max) {
        int n = 0;
        boolean flag = true;
        while (flag) {
            try {
                n = Integer.parseInt(input.nextLine());
                if (n < min || n > max) {
                    throw new NumberFormatException();
                } else {
                    flag = false;
                }
            } catch (NumberFormatException ex) {
                System.out.print("Number must be in range (" + min + "-" + max + "): ");
            }
        }
        return n;
    }

    /**
     * Gets an integer value from the user within a specified range.
     *
     * @param msg The message prompt to display.
     * @param exmsg The exception message to display when the value is outside
     * the range.
     * @param min The minimum allowed value (inclusive).
     * @param max The maximum allowed value (inclusive).
     * @return The integer value entered by the user.
     * @throws Exception If the value is outside the specified range or is not a
     * valid integer.
     */
    public static int getInt(String msg, String exmsg, int min, int max) throws Exception {
        int n = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print(msg);
                n = Integer.parseInt(input.nextLine());
                if (n < min || n > max) {
                    throw new Exception(exmsg);
                } else {
                    flag = false;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Wrong format! ");
            } catch (Exception ex) {
                System.out.println("Wrong format! " + ex.getMessage());
            }
        }
        return n;
    }

    /**
     * Method for inputting a double number within a specified range.
     *
     * @param min The minimum value of the number.
     * @param max The maximum value of the number.
     * @return A double number within the range entered by the user.
     */
    public static double getDouble(double min, double max) {
        double n = 0;
        boolean flag = true;
        while (flag) {
            try {
                n = input.nextDouble();
                if (n < min || n > max) {
                    throw new NumberFormatException();
                } else {
                    flag = false;
                }
            } catch (NumberFormatException ex) {
                System.out.print("Number must be in range (" + min + "-" + max + "): ");
            }
        }
        return n;
    }

    /**
     * Method for inputting a double number within a specified range.
     *
     * @param msg The message to display when prompting for input.
     * @param exmsg The exception message to display when the input is invalid.
     * @param min The minimum value of the number.
     * @param max The maximum value of the number.
     * @return A double number within the range entered by the user.
     * @throws Exception if the input is not a valid double or falls outside the
     * specified range.
     */
    public static double getDouble(String msg, String exmsg, double min, double max) throws Exception {
        double n = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print(msg);
                n = Double.parseDouble(input.nextLine());
                if (n < min || n > max) {
                    throw new Exception(exmsg);
                } else {
                    flag = false;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Wrong format! ");
            } catch (Exception ex) {
                System.out.println("Wrong format! " + ex.getMessage());
            }

        }
        return n;
    }

    /**
     * Method for inputting a Yes or No choice.
     *
     * @return The user's input, either "Y" or "N".
     */
    public static String getYesNo() {
        String n = null;
        boolean flag = true;
        while (flag) {
            n = input.nextLine();
            if (n.equalsIgnoreCase("y") || n.equalsIgnoreCase("n")) {
                flag = false;
            } else {
                System.out.println("Yes or No? (Y/N)");
            }
        }
        return n;
    }

    /**
     * Retrieves a boolean value based on user input.
     *
     * @param msg The message to prompt the user for input.
     * @return The boolean value chosen by the user.
     */
    public static boolean getBoolean(String msg) {
        String n;
        boolean choice = false;
        boolean flag = true;
        while (flag) {
            System.out.print(msg);
            n = input.nextLine();
            if (n.equalsIgnoreCase("y")) {
                choice = true;
                flag = false;
            } else if (n.equalsIgnoreCase("n")) {
                choice = false;
                flag = false;
            } else {
                System.out.println("Yes or No? (Y/N)");
            }
        }
        return choice;
    }

    /**
     * Method for inputting a string that matches a specified format using
     * regular expression.
     *
     * @param msg The message prompt for the input.
     * @param format The desired format of the string.
     * @param regex The regular expression pattern to match the string format.
     * @return The user's input string.
     */
    public static String getString(String msg, String format, String regex) {
        boolean flag = true;
        String str = null;
        while (flag) {
            try {
                System.out.print(msg);
                str = input.nextLine();
                Pattern pt = Pattern.compile(regex);
                if (!pt.matcher(str).find()) {
                    throw new Exception();
                } else {
                    flag = false;
                }
            } catch (Exception ex) {
                System.out.println(format);
            }
        }
        return str;
    }

    /**
     * Method for inputting a string.
     *
     * @param msg The message prompt for the input.
     * @return The user's input string.
     */
    public static String getString(String msg) {
        System.out.print(msg);
        return input.nextLine();
    }

    /**
     * Prompts the user to enter a non-null string.
     *
     * @param msg The message to display as a prompt.
     * @return The non-null string entered by the user.
     */
    public static String getStringNotNull(String msg) {
        boolean flag = true;
        String str = null;
        while (flag) {
            try {
                System.out.print(msg);
                str = input.nextLine();
                if (str.isEmpty()) {
                    throw new Exception("String must be not empty!");
                } else {
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return str;
    }

    /**
     * Prompts the user to enter a string from a given list of options.
     *
     * @param msg The message to display as a prompt.
     * @param strlist The list of valid string options.
     * @return The string entered by the user from the list of options.
     */
    public static String getStringInList(String msg, List<String> strlist) {
        boolean flag = true;
        String str = null;
        while (flag) {
            try {
                System.out.print(msg);
                str = input.nextLine();
                for (String st : strlist) {
                    if (str.equalsIgnoreCase(st)) {
                        flag = false;
                        str = st;
                        break;
                    }
                }
                if (flag == true) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Only accept as: " + strlist);
            }
        }
        return str;
    }

    /**
     * Prompts the user to enter a list of strings.
     *
     * @param msg The message to display as a prompt.
     * @return The list of strings entered by the user.
     */
    public static List<String> getStringList(String msg) {
        boolean flag = true;
        List<String> list = new ArrayList<>();
        while (flag) {
            System.out.print(msg);
            String str = input.nextLine();
            if (!str.isEmpty()) {
                list.add(str);
            } else {
                flag = false;
            }
        }
        return list;
    }

    /**
     * Method for inputting the gender (Male or Female).
     *
     * @return The user's input for gender.
     */
    public static String getGender() {
        boolean flag = true;
        String gender = null;
        while (flag) {
            try {
                System.out.print("Enter gender (Male or Female): ");
                gender = input.nextLine();
                if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
                    throw new Exception();
                } else {
                    flag = false;
                }
            } catch (Exception ex) {
                System.out.println("Gender must be Male or Female!");
            }
        }
        return gender;
    }

    /**
     * Method for inputting a phone number in the specified format.
     *
     * @param msg The message to display when prompting for input.
     * @return The user's input for the phone number.
     */
    public static String getPhone(String msg) {
        String phone = null;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print(msg);
                phone = input.nextLine();
                Pattern pt = Pattern.compile(phoneRegex);
                if (!pt.matcher(phone).find()) {
                    throw new Exception();
                } else {
                    flag = false;
                }
            } catch (Exception ex) {
                System.out.println("Number must be in phone number format!");
            }
        }
        return phone;
    }

    /**
     * Prompts the user to enter an email address with a specific domain.
     *
     * @param msg The message to display as a prompt.
     * @param domain The domain that the email address should end with.
     * @return The valid email address entered by the user.
     */
    public static String getMail(String msg, String domain) {
        String email = null;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print(msg);
                email = input.nextLine();
                Pattern pt = Pattern.compile(mailRegex);
                if (!email.endsWith(domain) || !pt.matcher(email).find()) {
                    throw new Exception("Email must only include letters, numbers and end with domain " + domain);
                } else {
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return email;
    }

    /**
     * Method for inputting a shift (Day or Night).
     *
     * @return The user's input for the shift.
     */
    public static String getShift() {
        boolean flag = true;
        String shift = null;
        while (flag) {
            try {
                System.out.print("Enter shift (Day or Night): ");
                shift = input.nextLine();
                if (!shift.equalsIgnoreCase("day") && !shift.equalsIgnoreCase("night")) {
                    throw new Exception();
                } else {
                    flag = false;
                }
            } catch (Exception ex) {
                System.out.println("Shift must be Day or Night!");
            }
        }
        return shift;
    }

    /**
     * Method for inputting a date in the specified format.
     *
     * @param msg The message to display for input.
     * @return The user's input for the date.
     * @throws java.text.ParseException
     */
    public static Date getDate(String msg) throws ParseException {
        boolean flag = true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = null;
        while (flag) {
            try {
                System.out.print(msg);
                date = input.nextLine();
                Pattern pt = Pattern.compile(dateFormat);
                if (!pt.matcher(date).find() || !isValidDate(date)) {
                    throw new Exception("the date must be in dd/mm/yyyy format!");
                } else {

                    flag = false;
                }
            } catch (Exception ex) {
                System.out.println("Wrong date format, " + ex.getMessage());
            }
        }
        return sdf.parse(date);
    }

    /**
     * Retrieves a Date object after validating and parsing the user input.
     *
     * @param msg The message to prompt the user for a date.
     * @param datebefore The date to compare the user input with.
     * @return The Date object representing the user's chosen date.
     * @throws ParseException If the user input cannot be parsed as a valid
     * date.
     */
    public static Date getDateAfter(String msg, Date datebefore) throws ParseException {
        String date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean flag = true;
        while (flag) {
            try {
                System.out.print(msg);
                date = input.nextLine();
                Pattern pt = Pattern.compile(dateFormat);

                if (!pt.matcher(date).find() || !isValidDate(date)) {
                    throw new Exception("the date must be in dd/mm/yyyy format!");
                } else {
                    if (sdf.parse(date).compareTo(datebefore) < 0) {
                        throw new Exception("your input date must be after " + sdf.format(datebefore));
                    }
                    flag = false;
                }
            } catch (Exception ex) {
                System.out.println("Wrong date format, " + ex.getMessage());
            }
        }
        return sdf.parse(date);
    }

    /**
     * Checks if a given date is valid.
     *
     * @param date The date to be validated.
     * @return True if the date is valid, false otherwise.
     */
    public static boolean isValidDate(String date) {
        String[] split = date.split("[-/. ]");
        boolean check = false;
        if (split.length == 3) {
            int day = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            int year = Integer.parseInt(split[2]);
            int maxDay = 30;
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                maxDay = 31;
            }
            if (month == 2) {
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                    maxDay = 29;
                } else {
                    maxDay = 28;
                }
            }
            check = day <= maxDay;
        }
        return check;
    }

    /**
     * Waits for the user to press the "ENTER" key to continue.
     */
    public static void pressEnterKey() {
        System.out.print("Press \"ENTER\" to continue...");
        input.nextLine();
    }
}
