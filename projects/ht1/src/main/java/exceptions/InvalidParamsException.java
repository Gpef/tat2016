package exceptions;

/**
 * Exception for causes of invalid command parameters.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public class InvalidParamsException extends CommandException {

    /**
     * Constructs a {@code CommandException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public InvalidParamsException(String message) {
        super(message);
    }
}
