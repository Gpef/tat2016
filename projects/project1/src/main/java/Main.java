import exceptions.RouteException;
import route.Route;
import route.RouteUtils;
import transport.*;
import transport.fuel.Fuel;

import java.io.File;
import java.io.IOException;

/**
 * Main class of the application.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 24.10.2016
 */
public class Main {

    private static String routeFilePath = "route.txt";

    // Fuel consumptions for 100 km
    private static double CAR_FUEL_CONSUMPTION = 5;
    private static double BUS_FUEL_CONSUMPTION = 25;
    private static double TARDIS_FUEL_CONSUMPTION = 10;

    /**
     * Entrance point to the program. Reads route from
     * file, creates objects of presented in program
     * moving means and shows their statistic by read
     * route.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            Route route = Route.readFromFile(new File(routeFilePath));

            CanPassRoute human = new Human();
            CanPassRoute bike = new Bicycle();
            CanPassRoute car = new Car(Fuel.PETROL, CAR_FUEL_CONSUMPTION, 1);
            CanPassRoute bus = new Bus(Fuel.DIESEL, BUS_FUEL_CONSUMPTION, 40);
            CanPassRoute tardis = new Tardis(Fuel.MERCURY, TARDIS_FUEL_CONSUMPTION, 1);

            System.out.format("Route length: %.2f km.\n", RouteUtils.calculateRouteLength(route));
            System.out.println(getRouteStats(human, route));
            System.out.println(getRouteStats(bike, route));
            System.out.println(getRouteStats(car, route));
            System.out.println(getRouteStats(bus, route));
            System.out.println(getRouteStats(tardis, route));

        } catch (IOException | RouteException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Make string that contains time spend for passing
     * the route and it's cost in according to fuel price.
     *
     * @param transportType route moving mean
     * @param route         route to count statistics
     * @return string that contains name of moving mean, time
     * spend to pass route and it's price
     */
    private static String getRouteStats(CanPassRoute transportType, Route route) {
        return transportType.getClass().getSimpleName() +
                String.format(" spend %f hours", transportType.calculateTime(route)) +
                String.format(", with price %.2f USD", transportType.calculateCost(route));
    }
}
