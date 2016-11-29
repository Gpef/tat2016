package exceptions;

/**
 * Main for exceptions connected with commands creating, execution etc.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 16.11.2016
 */
public class CommandException extends Exception {

    /**
     * Constructs a {@code CommandException} without
     * error detail message (message =  {@code null}).
     */
    public CommandException() {
        super();
    }

    /**
     * Constructs a {@code CommandException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public CommandException(String message) {
        super(message);
    }
}
