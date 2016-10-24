package main;

import main.figures.Rectangle;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Performs user input from console.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 21.10.2016
 */
public class ConsoleInput {

    /**
     * Gets input from user with showing message to him.
     * Collects two sides of rectangle and returns an object
     * that represents rectangle.
     *
     * @return rectangle object
     */
    public Rectangle getSquare() throws Exception {
        System.out.println("Input two sides: ");
        BigDecimal sideA = getNumber();
        BigDecimal sideB = getNumber();

        return new Rectangle(sideA, sideB);
    }

    /**
     * Get {@code BigDecimal} number from console input
     * while string that can be parsed to number inputted.
     *
     * @return string got from input
     */
    private BigDecimal getNumber() {
        while (true) {
            try {
                return new Scanner(System.in).nextBigDecimal();
            } catch (InputMismatchException e) {
                System.out.println("Can't parse it to nimber. Try again please.");
            }
        }
    }
}
