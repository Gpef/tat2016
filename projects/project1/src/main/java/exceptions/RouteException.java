package exceptions;

/**
 * Thrown if error occurred while working with {@code Route} routes.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 25.10.2016
 */
public class RouteException extends Exception {
    public RouteException(String message) {
        super(message);
    }
}
