package com.rct.homework.lesson2.task07.validator;

import com.rct.homework.lesson2.task07.localisation.MessageStrings;

/**
 * Validating strings for containing no numbers.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public class NoNumberValidator extends Validator {

    private String symbolsToFind = "\\D*$";

    @Override
    public boolean validate(String validateString) {
        return validateString.matches(symbolsToFind);
    }

    @Override
    public String getPassMessage() {
        return MessageStrings.NO_NUMBER_VALIDATOR_PASS;
    }
}
