package task07.validator;

import task07.Utils;
import task07.localisation.MessageStrings;

/**
 * Validating strings for containing more than 5 words.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public class WordsValidator extends Validator {

    @Override
    public boolean validate(String validateString) {
        return Utils.splitSentence(validateString).length > 5;
    }

    @Override
    public String getPassMessage() {
        return MessageStrings.WORDS_VALIDATOR_PASS;
    }
}
