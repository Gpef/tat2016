package task05.reader;

import task05.exceptions.WrongSidesNumberException;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Processing reading triangle data from user input in console.
 *
 * @author Oleg Baslak
 * @version 1.2
 * @since 03-10-2016
 */
public class ConsoleTriangleReader extends TriangleReader {

    /* Message string constants */
    private static final String NAN_ERROR_MESSAGE = "Can't parse it to number";
    private static final String TRY_AGAIN_MESSAGE = "Try again";

    @Override
    public BigDecimal[] readSides(int paramsNumber) throws WrongSidesNumberException {
        if (paramsNumber <= 0) {
            throw new WrongSidesNumberException();
        }

        BigDecimal[] input = new BigDecimal[paramsNumber];
        Scanner scanner = new Scanner(System.in);
        BigDecimal readNumber;
        int index = 0;
        while (index < paramsNumber) {
            try {
                readNumber = scanner.nextBigDecimal();
                input[index] = readNumber;
                System.out.println("read side: " + readNumber);
                index++;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("'" + scanner.next() + "'" + " " + NAN_ERROR_MESSAGE + " " + TRY_AGAIN_MESSAGE);
            }
        }
        return input;
    }
}
