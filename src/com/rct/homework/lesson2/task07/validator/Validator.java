package com.rct.homework.lesson2.task07.validator;

/**
 * Base class for validators
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public abstract class Validator {

    /**
     * Validates string for containing something
     * related to class that overrides this method
     *
     * @param validateString {@code string} to validate
     * @return {@code true} if string contains something
     * related to class extender
     */
    public abstract boolean validate(String validateString);
}
