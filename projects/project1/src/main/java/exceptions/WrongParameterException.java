package exceptions;

/**
 * Thrown when wrong parameter was send into methods
 * or constructors.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class WrongParameterException extends Exception {
    public WrongParameterException(String message) {
        super(message);
    }
}
