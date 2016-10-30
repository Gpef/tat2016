import exceptions.Messages;
import exceptions.WrongParameterException;
import route.Route;
import route.RouteUtils;
import route.reader.RouteFileReader;
import transport.*;
import transport.base.CanPassRoute;
import transport.fuel.Fuel;

import java.io.File;
import java.util.ArrayList;

/**
 * Main class of the application.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 24.10.2016
 */
public class Main {

    private static String routeFilePath = "route.txt";

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
            Route route = new RouteFileReader(new File(routeFilePath)).read();
            System.out.format("Route length: %.2f km.\n", new RouteUtils().calculateEuclidRouteLength(route));
            createMovingMeans().forEach(mean -> System.out.println(getRouteStats(mean, route)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Make string that contains time spend for passing
     * the route and it's cost in according to the fuel price.
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

    /**
     * Creates list of moving means to check route statistics.
     *
     * @return created {@code ArrayList} list of moving means.
     */
    private static ArrayList<CanPassRoute> createMovingMeans() {
        ArrayList<CanPassRoute> means = new ArrayList<>();
        try {
            means.add(new Human());
            means.add(new Bicycle());
            means.add(new Car(Fuel.PETROL, DefaultStats.CAR_FUEL_CONSUMPTION, 1, 1));
            means.add(new Bus(Fuel.DIESEL, DefaultStats.BUS_FUEL_CONSUMPTION, 40, 40));
            means.add(new Tardis(DefaultStats.TARDIS_FUEL_CONSUMPTION, 1, 1));
            return means;
        } catch (WrongParameterException e) {
            System.out.println(Messages.INFO + " " + e.getMessage());
        }
        return means;
    }
}
