package com.rct.homework.lesson2.task08.storage;

import com.rct.homework.lesson2.task08.exceptions.EmptyStorageException;
import com.rct.homework.lesson2.task08.exceptions.NoProductOfTypeException;

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

    public Storage() {
        productsAtStorage = new ArrayList<>();
    }

    /**
     * Adds <code>Product</code> to storage
     *
     * @param product product needs to be added
     */
    public void add(Product product) {
        productsAtStorage.add(product);
    }

    /**
     * Returns <code>ArrayList</code> of products of specified
     * type, if there is no products of that type - throws
     * <code>NoProductsOfTypeException</code>
     *
     * @param type type of product to return
     * @return array of products if there is at least
     * one product of that type at the storage
     * @throws NoProductOfTypeException if there is no any products of
     *                                  specified type at the storage
     */
    private ArrayList<Product> getProductsOfType(String type) throws NoProductOfTypeException {
        ArrayList<Product> productOfType = new ArrayList<>();
        for (Product nextProduct : productsAtStorage) {
            if (nextProduct.getType().toLowerCase().
                    equals(type.toLowerCase())) {
                productOfType.add(nextProduct);
            }
        }
        if (productOfType.size() == 0) {
            throw new NoProductOfTypeException();
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
        BigDecimal averagePrice = BigDecimal.ZERO;
        for (Product nextProduct : productsAtStorage) {
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
    public BigInteger findTypesAmount() throws EmptyStorageException {
        if (0 == productsAtStorage.size()) {
            throw new EmptyStorageException();
        }
        HashSet<String> types = new HashSet<>();
        for (Product nextProduct : productsAtStorage) {
            types.add(nextProduct.getType());
        }
        return BigInteger.valueOf(types.size());
    }

    /**
     * Finds average price of products with specified type.
     * If there is no products of specified type throws
     * <code>NoProductOfTypeException</code> exception.
     *
     * @param type type of products to calculate average price
     * @return average price of product of specified type,
     * @throws NoProductOfTypeException if there is no products of that
     *                                  type at the storage
     */
    public BigDecimal findAveragePriceOfType(String type) throws NoProductOfTypeException {
        ArrayList<Product> productsOfType = getProductsOfType(type);
        return findAveragePrice(productsOfType);
    }

    /**
     * Finds average price of all products at the storage.
     *
     * @return average price of all products at the storage
     * @throws EmptyStorageException if there is no products at
     *                               the storage (array size == 0)
     */
    public BigDecimal findAveragePriceOfProducts() throws EmptyStorageException {
        if (0 == productsAtStorage.size()) {
            throw new EmptyStorageException();
        }
        return findAveragePrice(productsAtStorage);
    }
}
