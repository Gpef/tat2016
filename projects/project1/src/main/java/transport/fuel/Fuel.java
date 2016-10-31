package transport.fuel;

import exceptions.WrongParameterException;

/**
 * Represents fuel that can be consumed by transports.
 * Contains fuel types and their prices in USD.
 * Prices can be lately changed via setPrice method.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 26.10.2016
 */
public enum Fuel {

    FOR_TESTS(10),
    PETROL(1),
    DIESEL(0.8),
    MERCURY(1);

    public String type;
    private double price;

    Fuel(double fuelPrice) {
        price = fuelPrice;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Set new fuel price. Before setting validates
     * new value. If it's not valid - throws
     * {@code WrongParameterException}.
     *
     * @param newPrice new fuel price
     * @throws WrongParameterException if fuel price <= 0
     */
    public void setPrice(double newPrice) throws WrongParameterException {
        validatePrice(newPrice);
        price = newPrice;
    }


    /**
     * Validates fuel price parameter. Throws {@code WrongParameterException}
     * with message if it has not valid value.
     *
     * @param validatePrice fuel price to validate
     * @throws WrongParameterException if fuel price <= 0
     */
    private void validatePrice(double validatePrice) throws WrongParameterException {
        if (Double.compare(validatePrice, 0) <= 0) {
            throw new WrongParameterException("Fuel price can't be <= 0");
        }
    }
}
