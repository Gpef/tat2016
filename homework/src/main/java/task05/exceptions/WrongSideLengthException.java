package task05.exceptions;

/**
 * Thrown if corrupted sides data was send into
 * Triangle constructor.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 03-10-2016
 */
public class WrongSideLengthException extends TriangleException {
    public WrongSideLengthException() {
        super("Side length can't be <= 0 or going to Infinite");
    }

    public WrongSideLengthException(String message) {
        super(message);
    }
}
