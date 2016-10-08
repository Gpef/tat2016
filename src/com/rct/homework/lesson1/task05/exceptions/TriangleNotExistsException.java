package com.rct.homework.lesson1.task05.exceptions;

/**
 * Thrown if triangle with chosen sides doesn't exist
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 03-10-2016
 */
public class TriangleNotExistsException extends Exception {

    public TriangleNotExistsException() {
        super("Triangle with that sides doesn't exist");
    }
}
