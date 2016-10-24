package main.task01;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Performs user input from console.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 21.10.2016
 */
public class ConsoleInput {

    private static String MESSAGE_FIRST_NAME = "Please, input first name:";
    private static String MESSAGE_LAST_NAME = "Please, input last name:";
    private static String MESSAGE_AGE = "Please, input age:";
    private static String MESSAGE_CONTINUE = "Print 'y' if you wanna add more humans?";

    private static final String COMMAND_WAITING_SYMBOL = ">";

    /**
     * Gets input from user with showing messages to him.
     * Collects last name, first name and age of human and adds
     * it to array list. If age is specified wrong, human won't be
     * created and error message will be shown.
     *
     * @return list of humans got from input
     */
    public ArrayList<Human> getHumans() {
        ArrayList<Human> humans = new ArrayList<>();

        String firstName;
        String lastName;
        int age;

        while (true) {
            firstName = getString(MESSAGE_FIRST_NAME);
            lastName = getString(MESSAGE_LAST_NAME);
            try {
                age = Integer.parseInt(getString(MESSAGE_AGE));
                humans.add(new Human(firstName, lastName, age));
            } catch (NumberFormatException e) {
                System.out.println("Can't parse it no age. Human wan't be created");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            String userCommand = getString(MESSAGE_CONTINUE);
            if (!userCommand.equals("y")) {
                break;
            }
        }

        return humans;
    }

    /**
     * Get string from console input with showing message to user.
     *
     * @param message message to show before getting input
     * @return string got from input
     */
    private String getString(String message) {
        System.out.print(message + "\n" + COMMAND_WAITING_SYMBOL + " ");
        return new Scanner(System.in).nextLine();
    }
}
