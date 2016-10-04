package com.rct.homework.lesson1.task05;

import com.rct.homework.lesson1.task05.exceptions.TriangleNotExistsException;
import com.rct.homework.lesson1.task05.exceptions.WrongSidesNumberException;

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
    static final String INPUT_REQUSET = "Input 3 sides of triangle:";

    static final int NUMBER_OF_ARGUMENTS = 3;


    /**
     * Entrance point to the app
     *
     * @param args command line arguments
     * @throws TriangleNotExistsException if triangle with inputted lengths
     *                                    don't exist
     */
    public static void main(String[] args) {
        System.out.println(INPUT_REQUSET);
        InputController inputController = new InputController();
        double[] sides = new double[0];
        try {
            sides = inputController.getInput(NUMBER_OF_ARGUMENTS);
        } catch (WrongSidesNumberException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        try {
            Triangle triangle = new Triangle(sides);
            System.out.println(triangle.getType());
        } catch (TriangleNotExistsException e) {
            System.out.println(e.getMessage());
        }

    }
}
