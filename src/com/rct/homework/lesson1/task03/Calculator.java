package com.rct.homework.lesson1.task03;

/**
 * Performing calculating operations with numbers
 *
 * @author Oleg Baslak
 * @version 1.1
 * @since 30-09-2016
 */
class Calculator {

    /**
     * Calculates needed operations and prints results to console
     *
     * @param number1 first number
     * @param number2 second number
     */
    static void calculate(double number1, double number2) {
        System.out.println(number1 + " + " + number2 + " = " + add(number1, number2));
        System.out.println(number1 + " - " + number2 + " = " + subtract(number1, number2));
        System.out.println(number1 + " * " + number2 + " = " + multiply(number1, number2));

        if (isZero(number2)) {
            System.out.println("Can't divide by zero");
        } else {
            System.out.println(number1 + " / " + number2 + " = " + divide(number1, number2));
        }
    }

    /**
     * Checks if number on ipnut == 0
     *
     * @param number number to check
     * @return {@code true} if {@code number} is zero
     * {@code false} otherwise
     */
    private static boolean isZero(double number) {
        if (Double.isInfinite(1. / number)) {
            return true;
        }
        return false;
    }

    /**
     * Performing adding number2 to number1
     *
     * @param number1 first summand
     * @param number2 second summand
     * @return sum of number1 and number2
     */
    private static double add(double number1, double number2) {
        return number1 + number2;
    }

    /**
     * Performing subtracting number2 from number1
     *
     * @param number1 minuend number
     * @param number2 subtrahend number
     * @return difference of number1 and number2
     */
    private static double subtract(double number1, double number2) {
        return number1 - number2;
    }

    /**
     * Performing multiplying number2 and number1
     *
     * @param number1 first multiplier
     * @param number2 second multiplier
     * @return multiplication of number1 and number2
     */
    private static double multiply(double number1, double number2) {
        return number1 * number2;
    }

    /**
     * Performing dividing number1 by number2
     *
     * @param number1 dividend number
     * @param number2 divider number
     * @return {@code} true if the arguments are numbers;
     * {@code} false otherwise.
     */
    private static double divide(double number1, double number2) {
        return number1 / number2;
    }

}
