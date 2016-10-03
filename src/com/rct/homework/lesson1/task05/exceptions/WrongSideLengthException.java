package com.rct.homework.lesson1.task05.exceptions;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03-10-2016
 */
class WrongSideLengthException extends Exception {
    public WrongSideLengthException(){
        super("Side length can't be lower then or equals zero");
    }

}
