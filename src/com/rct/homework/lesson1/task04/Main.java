package com.rct.homework.lesson1.task04;

import com.rct.homework.lesson1.task04.exceptions.ComplexRootsOnlyException;
import com.rct.homework.lesson1.task04.exceptions.NoRootsException;
import com.rct.homework.lesson1.task04.exceptions.WrongParamsSizeException;

/**
 * Entrance to the solution.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 30-09-2016
 */
class Main {

    /* Message constants */
    private static final String WRONG_INPUT_MESSAGE = "Three numbers in arguments were expected. Try again";
    private static final String EQUATION_SOLVED= "Roots of equation:";
    private static final String AND = "and";

    static final int NUMBER_OF_ARGUMENTS = 3;

    /**
     * Entrance point to the program. Creates {@code} SquareEquation object
     * that solving equation with params from args
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length != NUMBER_OF_ARGUMENTS) {
            System.out.println(WRONG_INPUT_MESSAGE);
            System.exit(1);
        }

        try {
            SquareEquation squareEquation = new SquareEquation(Validator.parse(args));
            double[] roots = squareEquation.findRoots();
            System.out.println(EQUATION_SOLVED + " " + roots[0] + " " + AND + " " + roots[1]);
        } catch (WrongParamsSizeException | ComplexRootsOnlyException | NoRootsException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
