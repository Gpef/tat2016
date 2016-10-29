package transport;

import route.Route;
import route.RouteUtils;
import transport.base.CanPassRoute;
import transport.fuel.Fuel;

/**
 * Represents police box for teleporting in space and time.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 26.10.2016
 */
public class Tardis implements CanPassRoute {

    private Fuel fuel;
    private double fuelConsumption;
    private double passengersCount;

    public Tardis(Fuel tardisFuel, double tardisFuelConsumption, int tardisPassengersCount) {
        fuelConsumption = tardisFuelConsumption;
        passengersCount = tardisPassengersCount;
        fuel = tardisFuel;
    }

    /**
     * @param route route to calculate spend time for
     *              selected passing type
     * @return 2 seconds, because tardis needs 2 seconds to
     * dissolve
     */
    @Override
    public double calculateTime(Route route) {
        return 5.5e-4;
    }

    @Override
    public double calculateCost(Route route) {
        return fuelConsumption / 100 * new RouteUtils().calculateEuclidRouteLength(route) * fuel.getPrice() / passengersCount;
    }
}
