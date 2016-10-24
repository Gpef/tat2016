package homework.task07.validator;

import homework.task07.Utils;
import homework.task07.localisation.MessageStrings;

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
