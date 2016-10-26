package transport.fuel;

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

    PETROL(1),
    DIESEL(0.8),
    MERCURY(1);


    public String type;
    private double price;

    private Fuel(double fuelPrice) {
        price = fuelPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double newPrice) {
        price = newPrice;
    }
}
