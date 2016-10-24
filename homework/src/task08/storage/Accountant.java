package task08.storage;

import task08.exceptions.StorageException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Performing calculations connected with products at storage.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 11.10.2016
 */
public class Accountant {

    /**
     * Finds average price of all products at the storage.
     *
     * @return average price of all products at the storage
     * @throws StorageException if there is no products at
     *                          the storage (array size == 0)
     */
    public BigDecimal findAveragePriceOfProducts(Storage storage) throws StorageException {
        if (0 == storage.getProductsAtStorage().size()) {
            throw new StorageException("Storage is empty");
        }
        return findAveragePrice(storage.getProductsAtStorage());
    }

    /**
     * Finds average price of products with specified type.
     * If there is no products of specified type throws
     * {@code ProductException} exception.
     *
     * @param type type of products to calculate average price
     * @return average price of product of specified type,
     * @throws StorageException if there is no products of that
     *                          type at the storage
     */
    public BigDecimal findAveragePriceOfType(Storage storage, String type) throws StorageException {
        ArrayList<Product> productsOfType = storage.getProductsOfType(type);
        return findAveragePrice(productsOfType);
    }

    /**
     * Returns amount of product types at the storage.
     *
     * @return amount of types at the storage
     * @throws StorageException if there is no any products at
     *                          the storage
     */
    public BigInteger findTypesAmount(Storage storage) throws StorageException {
        if (0 == storage.getTypesAtStorage().size()) {
            throw new StorageException("Storage is empty");
        }
        return BigInteger.valueOf(storage.getTypesAtStorage().size());
    }

    /**
     * Returns number of all products at the storage
     *
     * @return number of products at the storage
     */
    public int getProductsAmount(Storage storage) {
        return storage.getProductsAtStorage().size();
    }

    /**
     * Returns average price of products get from {@code ArrayList} products.
     *
     * @return average price of products. If products {@code ArrayList} is empty
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


}
