package task07.localisation;

/**
 * Abstract class containing constant string messages
 * that are shown to a user.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public abstract class MessageStrings {

    public static final String HELLO_MESSAGE = "Input your string to isZero: ";
    public static final String TRY_AGAIN_MESSAGE = "Please enter not blank line to isZero:";

    // Validators messages
    public static final String DICTIONARY_VALIDATOR_PASS = "> Strings contains words from dictionary ";
    public static final String NO_DIGIT_VALIDATOR_PASS = "> String doesn't contain any digits";
    public static final String ONLY_DIGITS_VALIDATOR_PASS = "> String contains digits only";
    public static final String WORDS_VALIDATOR_PASS = "> Strings has more that 5 words";
}
