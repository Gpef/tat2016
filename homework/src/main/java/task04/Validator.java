package task04;

/**
 * Validates String arguments
 *
 * @author Oleg Baslak
 * @version 1.1
 * @since 03-10-2016
 */

class Validator {

    static final String PARSE_ERROR = "Can't parse command line argument to number.";


    /**
     * Parses arguments {@code String[]} and returns
     * parsed to {@code double[]} array of coefficients
     *
     * @param args arguments from command line to parse
     * @return parsed coefficients
     * @throws NumberFormatException if some arguments can't be parsed to number
     */
    static double[] parse(String[] args) throws NumberFormatException{
        double[] parsedCoefficients = new double[Main.NUMBER_OF_ARGUMENTS];
        for (int i = 0; i < parsedCoefficients.length; i++) {
            parsedCoefficients[i] = Double.parseDouble(args[i]);
        }
        return parsedCoefficients;
    }

    /**
     * Checks if {@code}double number on input == 0
     *
     * @param number number to check
     * @return {@code true} if {@code number} is zero
     * {@code false} otherwise
     */
    static boolean isZero(double number) {
        return Double.isInfinite(1. / number);
    }
}
