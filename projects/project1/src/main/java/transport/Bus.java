package transport;

import exceptions.WrongParameterException;
import route.Checkpoint;
import route.Route;
import route.RouteUtils;
import transport.base.MotorVehicle;
import transport.fuel.Fuel;

import java.util.ArrayList;

import static transport.DefaultStats.BUS_AVERAGE_SPEED;

/**
 * Represents bus that is moving slow, but can carry many passengers.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 25.10.2016
 */
public class Bus extends MotorVehicle {

    /**
     * Creates new bus object. Performs parameters validating.
     *
     * @param busFuel               type of fuel
     * @param busFuelConsumption    fuel consumption per 100 km
     * @param busPassengersCount    passengers count
     * @param busPassengersCapacity max passengers capacity
     * @throws WrongParameterException if at least one parameter has
     *                                 not valid value and {@code Bus} object
     *                                 can't be created
     */
    public Bus(Fuel busFuel, double busFuelConsumption, int busPassengersCount, int busPassengersCapacity) throws WrongParameterException {
        validateFuelConsumption(busFuelConsumption);
        validatePassengers(busPassengersCount, busPassengersCapacity);
        validateSpeed(BUS_AVERAGE_SPEED);

        this.averageSpeed = BUS_AVERAGE_SPEED;
        this.fuel = busFuel;
        this.fuelConsumption = busFuelConsumption;
        this.passengersCount = busPassengersCount;
        this.passengersCapacity = busPassengersCapacity;
    }

    @Override
    public double calculateTime(Route route) {
        double routeTime = 0;
        ArrayList<Checkpoint> points = route.getCheckpoints();
        RouteUtils routeUtils = new RouteUtils();
        for (int i = 1; i < points.size(); i++) {
            routeTime += routeUtils.calculateEuclidDistance(points.get(i - 1), points.get(i)) / getSpeed();
        }
        return routeTime;
    }

    @Override
    public double calculateCost(Route route) {
        return this.fuelConsumption / 100 * fuel.getPrice() *
                new RouteUtils().calculateEuclidRouteLength(route) / this.passengersCount;
    }

}
