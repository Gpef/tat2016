package main.task01;

import java.util.ArrayList;

/**
 * Variant 1
 */
public class Main {

    private static String MESSAGE_MIN_AGE = "Minimal age is ";
    private static String MESSAGE_MAX_AGE = "Maximal age is ";
    private static String MESSAGE_AVERAGE_AGE = "Average age is ";

    /**
     * Entrance point
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Human> humans = new ConsoleInput().getHumans();

        Counter counter = new Counter();
        System.out.format(MESSAGE_MIN_AGE + counter.getMinAge(humans) + "\n" +
                        MESSAGE_MAX_AGE + counter.getMaxAge(humans) + "\n" +
                        MESSAGE_AVERAGE_AGE + "%.2f",
                counter.getAverageAge(humans));

    }
}
