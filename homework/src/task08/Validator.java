package task08;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Class for validation methods.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
abstract public class Validator {

    /**
     * Trying to parse string to {@code BigDecimal} and throws
     * {@code NumberFormatException} exception if it can't.
     *
     * @param stringToValid string to validate
     * @return true - if amount is valid, false - otherwise
     * @throws NumberFormatException if string can't be parsed to number
     */
    public static boolean isValidDecimal(String stringToValid) throws NumberFormatException {
        try {
            BigDecimal number = new BigDecimal(stringToValid);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error: Can't parse " + "'" + stringToValid + "'" + " to number");
        }
        return true;
    }

    /**
     * Trying to parse string to {@code BigInteger} and throws
     * {@code NumberFormatException} exception if it can't.
     *
     * @param stringToValid amount to validate
     * @return true - if amount is valid, false - otherwise
     * @throws NumberFormatException if string can't be parsed to nimber
     */
    public static boolean isValidInteger(String stringToValid) {
        try {
            BigInteger number = new BigInteger(stringToValid);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error: Can't parse " + "'" + stringToValid + "'" + " to number");
        }
        return true;
    }
}
