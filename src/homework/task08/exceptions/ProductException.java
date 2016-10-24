package homework.task08.exceptions;

/**
 * Thrown when errors with product occurred.
 * Errors like wrong price and etc.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 11.10.2016
 */
public class ProductException extends Exception {
    public ProductException(String message) {
        super(message);
    }
}
