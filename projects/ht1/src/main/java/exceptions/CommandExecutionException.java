package exceptions;

/**
 * Exceptions occurred while executing commands, for example: in command
 * which searching for element, that element wasn't found.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public class CommandExecutionException extends CommandException {

    /**
     * Constructs a {@code CommandExecutionException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public CommandExecutionException(String message) {
        super(message);
    }
}
