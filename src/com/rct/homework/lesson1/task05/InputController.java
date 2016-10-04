package com.rct.homework.lesson1.task05;

import com.rct.homework.lesson1.task05.exceptions.WrongSideLengthException;
import com.rct.homework.lesson1.task05.exceptions.WrongSidesNumberException;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Processing user input from console
 *
 * @author Oleg Baslak
 * @version 1.2
 * @since 03-10-2016
 */
class InputController {

    /* Message string constants */
    private static final String NAN_ERROR_MESSAGE = "Can't parse it to number";
    private static final String TRY_AGAIN_MESSAGE = "Try again";

    /**
     * Gets {@code} paramsNumber arguments from console input.
     * Ignores all NaN input and waiting for full array of arguments
     * with size of {@code} paramsNumber. paramsNumber must be > 0
     *
     * @param paramsNumber number of arguments to get
     * @return array with read arguments
     */
    double[] getInput(int paramsNumber) throws WrongSidesNumberException {
        if (paramsNumber <= 0){
            throw new WrongSidesNumberException();
        }

        double[] input = new double[paramsNumber];
        Scanner scanner = new Scanner(System.in);
        String readString = "";
        double readNumber;
        int index = 0;
        while (index < paramsNumber) {
            try {
                readString = scanner.next();
                readNumber = Double.parseDouble(readString);

                // Side can't be <= 0 or bigger then Double.MAX
                if (readNumber < 0 || Validator.isZero(readNumber) ||
                        Double.isInfinite(readNumber)) {
                    throw new WrongSideLengthException();
                }
                input[index] = readNumber;
                System.out.println("read side: " + readNumber);
                index++;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("'" + readString + "'" + " " + NAN_ERROR_MESSAGE + ". " + TRY_AGAIN_MESSAGE);
            } catch (WrongSideLengthException e) {
                System.out.println(e.getMessage() + ". " + TRY_AGAIN_MESSAGE);
            }
        }
        return input;
    }

}
