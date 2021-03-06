package task03;

/**
 * Entrance point to the program
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 30-09-2016
 */
class Main {

    /* Message constants */
    private static final String WRONG_INPUT_MESSAGE = "Two numbers were expected. Try again";

    private static final int NUMBER_OF_ARGUMENTS = 2;

    /**
     * Entrance to solution. Making arithmetic operations with numbers on input
     *
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        if (args.length != NUMBER_OF_ARGUMENTS) {
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

