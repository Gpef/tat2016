package transport;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 27.10.2016
 */
public abstract class DefaultStats {

    // Fuel consumptions for 100 km
    public static double CAR_FUEL_CONSUMPTION = 5;
    public static double BUS_FUEL_CONSUMPTION = 25;
    public static double TARDIS_FUEL_CONSUMPTION = 10;


    // Default average speed in km per hour
    public static double CAR_AVERAGE_SPEED = 90;
    public static double BUS_AVERAGE_SPEED = 70;
    public static double BICYCLE_AVERAGE_SPEED = 16;
    public static double HUMAN_AVERAGE_SPEED = 6;

    // Default passengers capacities
    public static int CAR_PASSENGERS_CAPACITY = 5;
    public static int BUS_PASSENGERS_CAPACITY = 40;
    public static int TARDIS_PASSENGERS_CAPACITY = 3;

}

