package com.rct.homework.lesson2.task07.validator;

import com.rct.homework.lesson2.task07.localisation.MessageStrings;

/**
 * Validating strings for containing numbers only.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public class OnlyNumberValidator extends Validator {

    //  regex means "only numbers in string"
    private String symbolsToFind = "[\\d\\s]*$";

    @Override
    public boolean validate(String validateString) {
        return validateString.matches(symbolsToFind);
    }

    @Override
    public String getPassMessage() {
        return MessageStrings.ONLY_NUMBER_VALIDATOR_PASS;
    }
}
