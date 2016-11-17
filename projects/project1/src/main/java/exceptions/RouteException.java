package exceptions;

/**
 * Exception thrown if error while processing work
 * with {@code Route} route occurred (like not corresponding
 * to route creation rules).
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 27.10.2016
 */
public class RouteException extends Exception {
    public RouteException(String message) {
        super(message);
    }
}
