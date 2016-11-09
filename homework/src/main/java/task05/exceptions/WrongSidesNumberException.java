package task05.exceptions;

/**
 * Thrown if wrong sides number was set.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 03-10-2016
 */
public class WrongSidesNumberException extends TriangleException {
    public WrongSidesNumberException() {
        super("Triangle must have 3 sides only. Because it's triangle");
    }
}
