package homework.task07.validator;

import homework.task07.localisation.MessageStrings;

/**
 * Validating strings for containing numbers only.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public class OnlyDigitsValidator extends Validator {

    //  regex means "only numbers (with whitespaces) in string" ("212 12312 123 1" for example),
    //  strings starts from digit following after space will match (" 2" for example)
    private String symbolsToFind = "^(\\d|\\s\\d)[\\d\\s]*$";

    @Override
    public boolean validate(String validateString) {
        return validateString.matches(symbolsToFind);
    }

    @Override
    public String getPassMessage() {
        return MessageStrings.ONLY_DIGITS_VALIDATOR_PASS;
    }
}
