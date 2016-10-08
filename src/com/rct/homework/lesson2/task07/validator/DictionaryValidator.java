package com.rct.homework.lesson2.task07.validator;

import com.rct.homework.lesson2.task07.Utils;
import com.rct.homework.lesson2.task07.localisation.MessageStrings;

import java.util.HashSet;

/**
 * Validating strings for containing words from dictionary.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public class DictionaryValidator extends Validator {

    private HashSet<String> dictionary;

    public DictionaryValidator() {
        this.dictionary = initDictionary();
    }

    /**
     * Initialises words for dictionary.
     *
     * @return initialised dictionary
     */
    private HashSet<String> initDictionary() {
        HashSet<String> dictionary = new HashSet<String>();
        dictionary.add("Oleg");
        dictionary.add("Test");
        dictionary.add("Java");
        dictionary.add("Fire");
        return dictionary;
    }

    @Override
    public boolean validate(String validateString) {
        String[] splitSentence = Utils.splitSentence(validateString);
        for (String word : splitSentence) {
            if (dictionary.contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getPassMessage() {
        return MessageStrings.DICTIONARY_VALIDATOR_PASS;
    }
}
