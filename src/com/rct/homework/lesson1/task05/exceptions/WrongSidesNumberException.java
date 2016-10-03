package com.rct.homework.lesson1.task05.exceptions;

/**
 * Thrown if {@code}sides array don't have 3 variables
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 03-10-2016
 */
class WrongSidesNumberException extends Exception{
    public WrongSidesNumberException(){
        super("Triangle must have 3 sides only. Because it's triangle");
    }
}
