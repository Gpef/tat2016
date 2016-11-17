package exceptions;

/**
 * Thrown if error occurred when working with transports.
 * For example, bad results of calculations.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 01.11.2016
 */
public class RoutePassingException extends Exception {
    public RoutePassingException(String message) {
        super(message);
    }
}
