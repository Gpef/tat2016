package task05;

import task05.exceptions.TriangleNotExistsException;
import task05.exceptions.WrongSidesNumberException;

/**
 * Entrance point to the app. Get sides of triangle from
 * console input and prints type of that triangle
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 03-10-2016
 */
class Main {

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
        InputController inputController = new InputController();
        try {
            double[] sides = inputController.getInput(NUMBER_OF_ARGUMENTS);
            Triangle triangle = new Triangle(sides);
            System.out.println(triangle.getType());
        } catch (WrongSidesNumberException | TriangleNotExistsException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
