package task07.validator;

/**
 * Base class for validators.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public abstract class Validator {

    /**
     * Validates string for containing something
     * related to the rule of class that overrides this method.
     *
     * @param validateString <code>string</code> to isZero
     * @return <code>true</code> if rule is respected,
     * <code>false</code> otherwise
     */
    public abstract boolean validate(String validateString);

    /**
     * Returns message, if string pass validation.
     *
     * @return pass message
     */
    public abstract String getPassMessage();
}
