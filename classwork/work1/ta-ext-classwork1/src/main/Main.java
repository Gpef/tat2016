package main;

import java.util.Random;

/**
 * Main class of the application. Prints message
 * "Hello, world!
 * And hi again!"
 * and random number of "!"
 */
public class Main {

    /**
     * Entrance point to the program. Prints
     * "Hello, world!"
     * "And hi again!"
     * and random number of "!"after them.
     */
    public static void main(String[] args) {
        System.out.println("Hello, world!\n" +
                "And hi again!\n" +
                getRandomPunct());
    }

    /**
     * Counts random number from 5 to 50 and returns
     * String contains '!' this random times.
     *
     * @return string with randomised '!'
     */
    public static String getRandomPunct() {
        int random = new Random().nextInt(45) + 5;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < random; i++) {
            result.append("!");
        }
        return result.toString();
    }
}
