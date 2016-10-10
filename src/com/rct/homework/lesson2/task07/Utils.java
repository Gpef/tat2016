package com.rct.homework.lesson2.task07;

/**
 * Some helping functions
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public abstract class Utils {

    /**
     * Splits sentence into words by punctuation symbols and spaces
     *
     * @param sentence string to split
     * @return array of split words
     */
    public static String[] splitSentence(String sentence) {
        return sentence.split("(\\p{Punct})+|(\\s+)");
    }
}
