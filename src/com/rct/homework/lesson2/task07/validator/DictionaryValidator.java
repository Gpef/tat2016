package com.rct.homework.lesson2.task07.validator;

import java.util.HashSet;

/**
 * Validating strings for containing words from dictionary
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public class DictionaryValidator extends Validator {

    private HashSet<String> dictionary;

    @Override
    public boolean validate(String validateString) {
        return false;
    }

    private HashSet<String> initDictionary() {
        HashSet<String> dictionary = new HashSet<String>();
        dictionary.add("Oleg");
        dictionary.add("Test");
        dictionary.add("Java");
        dictionary.add("Fire");
        return dictionary;
    }
}
