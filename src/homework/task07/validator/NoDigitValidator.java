package homework.task07.validator;

import homework.task07.localisation.MessageStrings;

/**
 * Validating strings for containing no numbers.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public class NoDigitValidator extends Validator {

    //  regex means "no numbers in string"
    private String symbolsToFind = "^\\D*$";

    @Override
    public boolean validate(String validateString) {
        return validateString.matches(symbolsToFind);
    }

    @Override
    public String getPassMessage() {
        return MessageStrings.NO_DIGIT_VALIDATOR_PASS;
    }
}
