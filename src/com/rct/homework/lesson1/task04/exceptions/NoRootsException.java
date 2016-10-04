package com.rct.homework.lesson1.task04.exceptions;

/**
 * Exception thrown when equation doesn't have any roots
 * or there no method to find them
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 04-10-2016
 */
public class NoRootsException extends Exception {
    public NoRootsException() {
        super("Equation don't has any roots");
    }

}
