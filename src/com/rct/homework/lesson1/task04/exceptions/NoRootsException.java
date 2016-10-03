package com.rct.homework.lesson1.task04.exceptions;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 04-10-2016
 */
public class NoRootsException extends Exception {
    public NoRootsException(){
        super("Equation don't has any roots");
    }

}
