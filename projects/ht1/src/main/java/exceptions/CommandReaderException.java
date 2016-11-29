package exceptions;

/**
 * Base class for error occurred with commands readers.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 16.11.2016
 */
public class CommandReaderException extends Exception {

    /**
     * Constructs a {@code CommandReaderException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public CommandReaderException(String message) {
        super(message);
    }
}
