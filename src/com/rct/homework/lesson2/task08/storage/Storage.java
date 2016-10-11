package com.rct.homework.lesson2.task08.storage;

import com.rct.homework.lesson2.task08.exceptions.StorageException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Storage for keeping and managing products.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public class Storage {

    private ArrayList<Product> productsAtStorage;
    private HashSet typesAtStorage;

    public Storage() {
        productsAtStorage = new ArrayList<>();
        typesAtStorage = new HashSet<>();
    }

    /**
     * Adds <code>Product</code> to storage in an amount of <code>amount</code>
     *
     * @param product product needs to be added
     */
    public void add(Product product, BigInteger amount) throws StorageException {
        if (amount.compareTo(BigInteger.valueOf(0)) == -1 ||
                amount.compareTo(BigInteger.valueOf(0)) == 0) {
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
     * Returns <code>ArrayList</code> of products of specified
     * type, if there is no products of that type - throws
     * <code>NoProductsOfTypeException</code>
     *
     * @param type type of product to return
     * @return array of products if there is at least
     * one product of that type at the storage
     * @throws StorageException if there is no any products of
     *                          specified type at the storage
     */
    private ArrayList<Product> getProductsOfType(String type) throws StorageException {
        if (!typesAtStorage.contains(type)) {
            throw new StorageException("No products of that type at the storage");
        }
        ArrayList<Product> productOfType = new ArrayList<>();
        for (Product nextProduct : productsAtStorage) {
            if (nextProduct.getType().toLowerCase().
                    equals(type.toLowerCase())) {
                productOfType.add(nextProduct);
            }
        }
        return productOfType;
    }

    /**
     * Returns average price of a set of products
     *
     * @return average price of products. If products array is empty
     * return 0
     */
    private BigDecimal findAveragePrice(ArrayList<Product> products) {
        if (products.size() == 0) {
            return BigDecimal.valueOf(0);
        }
        BigDecimal averagePrice = BigDecimal.ZERO;
        for (Product nextProduct : products) {
            averagePrice = averagePrice.add(nextProduct.getPrice());
        }
        return averagePrice.divide(BigDecimal.valueOf(products.size()), 2, RoundingMode.HALF_UP);
    }

    /**
     * Returns number of all products at the storage
     *
     * @return number of products at the storage
     */
    public int getProductsAmount() {
        return productsAtStorage.size();
    }

    /**
     * Returns amount of product types at the storage
     *
     * @return amount of types at the storage
     */
    public BigInteger findTypesAmount() throws StorageException {
        if (0 == typesAtStorage.size()) {
            throw new StorageException("Storage is empty");
        }
        return BigInteger.valueOf(typesAtStorage.size());
    }

    /**
     * Finds average price of products with specified type.
     * If there is no products of specified type throws
     * <code>ProductException</code> exception.
     *
     * @param type type of products to calculate average price
     * @return average price of product of specified type,
     * @throws StorageException if there is no products of that
     *                          type at the storage
     */
    public BigDecimal findAveragePriceOfType(String type) throws StorageException {
        ArrayList<Product> productsOfType = getProductsOfType(type);
        return findAveragePrice(productsOfType);
    }

    /**
     * Finds average price of all products at the storage.
     *
     * @return average price of all products at the storage
     * @throws StorageException if there is no products at
     *                          the storage (array size == 0)
     */
    public BigDecimal findAveragePriceOfProducts() throws StorageException {
        if (0 == productsAtStorage.size()) {
            throw new StorageException("Storage is empty");
        }
        return findAveragePrice(productsAtStorage);
    }

    /**
     * Debug purposes. Initialises ready-to-use storage
     *
     * @return initialised storage
     */
    public static Storage init() {
        Storage storage = new Storage();
        try {
            storage.add(new Product("Pen", "COCO", new BigDecimal("1000")), BigInteger.valueOf(2));
            storage.add(new Product("Pen", "COCO1", new BigDecimal("1000")), BigInteger.valueOf(3));
            storage.add(new Product("Pen", "COCO2", new BigDecimal("1000")), BigInteger.valueOf(4));
            storage.add(new Product("Pencil", "COCO", new BigDecimal("1000")), BigInteger.valueOf(5));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return storage;
    }
}
