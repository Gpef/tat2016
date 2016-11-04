package task03;

/**
 * Validates String arguments
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 30-09-2016
 */
class Validator {

    /**
     * Checks if args has exactly numbers
     *
     * @param args String to check
     * @return {@code} true, if args are numbers;
     * {@code} false otherwise
     */
    static boolean validate(String[] args) {

        try {
            System.out.println("Your numbers: " + Double.parseDouble(args[0]) + " and " + Double.parseDouble(args[1]));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
