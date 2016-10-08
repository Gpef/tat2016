package com.rct.homework.lesson2.task08.storage;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * Storage for keeping and managing products.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public abstract class Storage {

    private static int size = 0;
    private HashMap<Product, BigInteger> productsAtStorage;

    public void add(Product product, BigInteger amount){

    }

}
