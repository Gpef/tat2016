package com.rct.homework.lesson1.task03;

/**
 * Entrance point to the program
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 30-09-2016
 */
public class Main {

    /* Message constants */
    public static final String WRONG_INPUT_MESSAGE = "Two numbers were expected. Try again";

    /**
     * Entrance to solution. Making arithmetic operations with numbers on input
     *
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(WRONG_INPUT_MESSAGE);

            System.exit(1);
        }
        if (Validator.validate(args)) {
            Calculator.calculate(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        } else {
            System.out.println(WRONG_INPUT_MESSAGE);

            System.exit(1);
        }
    }
}

