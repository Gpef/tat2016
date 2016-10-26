package transport.fuel;

/**
 * Represents fuel that can be consumed by transports.
 * Contains fuelType and its price in USD.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 26.10.2016
 */
public class Fuel {

    public FuelType type;

    private double price;

    public Fuel(FuelType fuelType, double fuelPrice){
        type = fuelType;
        price = fuelPrice;
    }

    public double getPrice() {
        return price;
    }
}
