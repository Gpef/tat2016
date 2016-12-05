package autotests.utils.exceptions;

/**
 * Exception for error with autotests.config reading, parsing.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.11.2016
 */
public class ConfigException extends Exception {
    public ConfigException() {
        super();
    }

    public ConfigException(String message) {
        super(message);
    }
}
