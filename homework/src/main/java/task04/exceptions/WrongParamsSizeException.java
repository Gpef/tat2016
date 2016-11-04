package task04.exceptions;

/**
 * Exception thrown if args don't have {@code} Main.NUMBER_OF_ARGUMENTS
 * arguments
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 03-10-2016
 */
public class WrongParamsSizeException extends Exception {
    public WrongParamsSizeException() {
        super("Params array must contain 3 params as a,b and c coefficients of equation");
    }

}
