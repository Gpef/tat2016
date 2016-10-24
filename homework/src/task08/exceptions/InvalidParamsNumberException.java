package task08.exceptions;

/**
 * Exception thrown if options provider has invalid number
 * of options -> that means user inputted incorrect data.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 11.10.2016
 */
public class InvalidParamsNumberException extends Exception {
    public InvalidParamsNumberException(String message) {
        super(message);
    }
}
