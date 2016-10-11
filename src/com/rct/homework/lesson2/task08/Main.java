package com.rct.homework.lesson2.task08;

import com.rct.homework.lesson2.task08.exceptions.ProductException;
import com.rct.homework.lesson2.task08.exceptions.StorageException;
import com.rct.homework.lesson2.task08.storage.Storage;

/**
 * Main class of the application
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public class Main {

    /**
     * Entrance point to the application
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Storage storage = new Storage();
        Dialogs dialogs = new Dialogs();
        try {
            dialogs.start(storage);
        } catch (ProductException | StorageException e) {
            System.out.println(e.getMessage());
        }

    }
}
