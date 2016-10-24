package homework.task04;

import homework.task04.exceptions.ComplexRootsOnlyException;
import homework.task04.exceptions.NotQuadraticEquationException;
import homework.task04.exceptions.WrongParamsSizeException;

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
    private static final String EQUATION_SOLVED = "Roots: %f and %f";

    static final int NUMBER_OF_ARGUMENTS = 3;

    /**
     * Entrance point to the program. Creates {@code SquareEquation} object
     * that solving equation with params from args
     *
     * @param args command line arguments
     * @see SquareEquation
     */
    public static void main(String[] args) {
        if (args.length != NUMBER_OF_ARGUMENTS) {
            System.out.println(WRONG_INPUT_MESSAGE);
            System.exit(1);
        }
        try {
            SquareEquation squareEquation = new SquareEquation(Validator.parse(args));
            double[] roots = squareEquation.findRoots();
            System.out.println(String.format(EQUATION_SOLVED, roots[0], roots[1]));
        } catch (WrongParamsSizeException | ComplexRootsOnlyException |
                NotQuadraticEquationException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println(Validator.PARSE_ERROR);
            System.exit(1);
        }
    }
}
