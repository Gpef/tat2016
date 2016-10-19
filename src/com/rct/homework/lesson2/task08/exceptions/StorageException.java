package com.rct.homework.lesson2.task08.exceptions;

/**
 * Exceptions connected with storage managing. Thrown when operations
 * can't be done because storage is empty or there is no product of
 * specified type or trying add <= 0 of products and etc.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 11.10.2016
 */
public class StorageException extends Exception {
    public StorageException(String message) {
        super(message);
    }
}
