package task05;

import task05.exceptions.TriangleException;
import task05.reader.ConsoleTriangleReader;

import java.math.BigDecimal;

/**
 * Entrance point to the app. Get sides of triangle from
 * console input and prints type of that triangle
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 03-10-2016
 */
public class Main {

    /* String constants (messages, errors etc.) */
    private static final String INPUT_REQUEST = "Input 3 sides of triangle:";

    private static final int NUMBER_OF_ARGUMENTS = 3;


    /**
     * Entrance point to the app
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println(INPUT_REQUEST);
        ConsoleTriangleReader consoleTriangleReader = new ConsoleTriangleReader();
        try {
            BigDecimal[] sides = consoleTriangleReader.readSides(NUMBER_OF_ARGUMENTS);
            Triangle triangle = new Triangle(sides[0], sides[1], sides[2]);
            System.out.println(triangle.getTypeName());
        } catch (TriangleException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
