package task04.exceptions;

/**
 * Exception thrown if equation isn't quadratic
 * (when A coefficient equals zero)
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 04.10.2016
 */
public class NotQuadraticEquationException extends Exception {
    public NotQuadraticEquationException() {
        super("This equation isn't quadratic");
    }
}
