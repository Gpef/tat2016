package com.rct.homework.lesson2.task08.exceptions;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 09.10.2016
 */
public class PriceParseException extends Exception {
    public PriceParseException(){
        super("Can't parse specified string to price");
    }
}
