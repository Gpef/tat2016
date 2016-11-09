package task05.exceptions;

/**
 * Root class for all exceptions connected with triangles.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 10.11.2016
 */
public class TriangleException extends Exception {
    public TriangleException() {
        super();
    }

    public TriangleException(String message) {
        super(message);
    }
}
