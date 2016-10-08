package com.rct.homework.lesson2.task08.storage;

import java.math.BigDecimal;

/**
 * Class that represents product at the storage.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public class Product {

    private String type;
    private String name;
    private BigDecimal price;

    public Product(String _type, String _name, String _price){
        type = _type;
        name = _name;
        price = new BigDecimal(_price);
        price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
