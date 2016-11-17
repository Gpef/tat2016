package exceptions;

/**
 * Exception thrown when error with route reading occurred
 * (when route wasn't found in file or route source wasn't found).
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 27.10.2016
 */
public class RouteReaderException extends Exception {
    public RouteReaderException(String message) {
        super(message);
    }
}
