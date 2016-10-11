package com.rct.homework.lesson2.task08.storage;

import com.rct.homework.lesson2.task08.exceptions.StorageException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Storage for keeping and managing products.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public class Storage {

    private ArrayList<Product> productsAtStorage;
    private HashSet<String> typesAtStorage;

    public Storage() {
        productsAtStorage = new ArrayList<>();
        typesAtStorage = new HashSet<>();
    }

    /**
     * Adds {@code Product} to storage in specified amount.
     * Amount must be > 0. Otherwise - throws {@code StorageException}
     * exception saying that amount must be > 0.
     *
     * @param product product needs to be added
     * @throws StorageException if amount value os <= 0
     */
    public void add(Product product, BigInteger amount) throws StorageException {
        if (!checkAmount(amount)) {
            throw new StorageException("Can't added lower than or equal to zero product");
        }
        for (BigInteger i = BigInteger.valueOf(0);
             i.compareTo(amount) == -1;
             i = i.add(BigInteger.valueOf(1))) {
            productsAtStorage.add(product);
        }
        typesAtStorage.add(product.getType());
        System.out.println("added " + amount + " " + product.toString());
    }

    /**
     * Checks if amount variable has acceptable value.
     * If it's <= 0 it returns {@code false}.
     *
     * @param amount amount to check
     * @return {@code true} if amount is > 0,
     * {@code false} - otherwise
     */
    private boolean checkAmount(BigInteger amount) {
        return !(amount.compareTo(BigInteger.valueOf(0)) == -1 ||
                amount.compareTo(BigInteger.valueOf(0)) == 0);
    }

    /**
     * Returns {@code ArrayList} of products of specified
     * type, if there is no products of that type - throws
     * {@code NoProductsOfTypeException} exception.
     *
     * @param type type of product to return
     * @return array of products if there is at least
     * one product of specified type at the storage
     * @throws StorageException if there is no any products of
     *                          specified type at the storage
     */
    public ArrayList<Product> getProductsOfType(String type) throws StorageException {
        if (!typesAtStorage.contains(type)) {
            throw new StorageException("No products of that type at the storage");
        }
        return productsAtStorage.stream().filter(nextProduct -> nextProduct.getType().toLowerCase().
                equals(type.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @return {@code ArrayList} of all products at the storage
     */
    public ArrayList<Product> getProductsAtStorage() {
        return productsAtStorage;
    }

    /**
     * @return {@code HashSet} of all types of products at the storage
     */
    public HashSet<String> getTypesAtStorage() {
        return typesAtStorage;
    }
}
