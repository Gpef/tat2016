package utils.exceptions;

/**
 * Exceptions thrown while working with database.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 12.12.2016
 */
public class DatabaseException extends Exception {
    public DatabaseException() {
        super();
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(Throwable e){
        super(e);
    }
}
