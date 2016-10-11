package com.rct.homework.lesson2.task08;

import com.rct.homework.lesson2.task08.exceptions.ProductException;
import com.rct.homework.lesson2.task08.exceptions.StorageException;

import java.math.BigDecimal;

/**
 * Class for validation methods.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
abstract public class Validator {

    /**
     * Checks if string that must be parsed to <code>BigDecimal</code>
     * price can be parsed and if it can checks if price not <= 0.
     *
     * @param price price to check
     * @return true - if price is valid, false - otherwise
     * @throws ProductException if occurred parsing error or
     *                          price <= 0
     */
    public static boolean isValidPrice(String price) throws ProductException {
        BigDecimal parsedPrice;
        try {
            parsedPrice = new BigDecimal(price);
        } catch (Exception e) {
            throw new ProductException("Error: Can't parse " + "'" + price + "'" + " to number");
        }
        if (parsedPrice.compareTo(BigDecimal.valueOf(0)) == -1 ||
                parsedPrice.compareTo(BigDecimal.valueOf(0)) == 0) {
            throw new ProductException("Error: Product price can't be <= 0");
        }
        return true;
    }

    /**
     * Checks if string that must be parsed to {@code BigInteger}
     * amount can be parsed and if it can checks if amount not <= 0.
     *
     * @param amount amount to check
     * @return true - if amount is valid, false - otherwise
     * @throws StorageException if occurred parsing error or
     *                          amount <= 0
     */
    public static boolean isValidAmount(String amount) throws StorageException {
        BigDecimal parsedAmount;
        try {
            parsedAmount = new BigDecimal(amount);
        } catch (Exception e) {
            throw new StorageException("Error: Can't parse " + "'" + amount + "'" + " to number");
        }
        if (parsedAmount.compareTo(BigDecimal.valueOf(0)) == -1 ||
                parsedAmount.compareTo(BigDecimal.valueOf(0)) == 0) {
            throw new StorageException("Error: Products amount can't be <= 0");
        }
        return true;
    }
}
