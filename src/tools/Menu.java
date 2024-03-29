package tools;

/**
 * This class provides a method for displaying a menu and prompting the user to
 * select an option.
 *
 * @author Nguyen Truong Tho
 */
public class Menu {

    /**
     * Displays the menu options and prompts the user to enter a choice.
     *
     * @param options An array of options to display in the menu.
     * @return The selected choice as an integer.
     */
    public static int getChoice(String[] options) {

        System.out.println("=======================MENU==========================");
        for (int i = 0; i < options.length; i++) {
            System.out.printf("|%2d. %-46s |\n", i + 1, options[i]);
        }
        System.out.println("=====================================================");
        System.out.print("Your options from 1 - " + options.length + ": ");
        return InputFormatter.getInt(1, options.length);
    }
}
