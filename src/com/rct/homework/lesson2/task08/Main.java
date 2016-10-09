package com.rct.homework.lesson2.task08;

import com.rct.homework.lesson2.task08.exceptions.EmptyStorageException;
import com.rct.homework.lesson2.task08.exceptions.NoProductOfTypeException;
import com.rct.homework.lesson2.task08.storage.Product;
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

        initStorage(storage);

        try {
            System.out.println("Products at the storage: " + storage.getProductsAmount());
            System.out.println("There is " + storage.findTypesAmount() + " types of products at the storage");
            System.out.println("Average price of type Pen: " + storage.findAveragePriceOfType("Pen"));
        } catch (EmptyStorageException | NoProductOfTypeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void initStorage(Storage storage) {
        try {
            storage.add(new Product("Pen", "COCO", "1000"));
            storage.add(new Product("Pen", "COCO1", "1000"));
            storage.add(new Product("Pen", "COCO2", "1000"));
            storage.add(new Product("Pencil", "COCO", "1000"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
