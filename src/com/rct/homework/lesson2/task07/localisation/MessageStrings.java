package com.rct.homework.lesson2.task07.localisation;

/**
 * Abstract class containing constant strings
 * that are shown to a user.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public abstract class MessageStrings {

    public static final String HELLO_MESSAGE = "Input your string to validate: ";

    // Validators messages
    public static final String DICTIONARY_VALIDATOR_PASS = "> Strings contains words from dictionary ";
    public static final String NO_NUMBER_VALIDATOR_PASS = "> String doesn't contain any numbers";
    public static final String ONLY_NUMBER_VALIDATOR_PASS = "> String contains numbers only";
    public static final String WORDS_VALIDATOR_PASS = "> Strings has more that 5 words";
}
