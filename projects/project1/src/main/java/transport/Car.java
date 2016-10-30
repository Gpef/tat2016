package transport;

import exceptions.WrongParameterException;
import route.Checkpoint;
import route.Route;
import route.RouteUtils;
import transport.base.MotorVehicle;
import transport.fuel.Fuel;

import java.util.ArrayList;

/**
 * Represents common car that is moving fast and has little
 * fuel consumption.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 25.10.2016
 */
public class Car extends MotorVehicle {

    /**
     * Creates new car object. Performs parameters validating.
     *
     * @param carFuel         fuel type
     * @param fuelConsumption fuel consumption
     * @param passengersCount passengers count
     */
    public Car(Fuel carFuel, double fuelConsumption, int passengersCount, int passengersCapacity) throws WrongParameterException {
        validateFuelConsumption(fuelConsumption);
        validateSpeed(DefaultStats.CAR_AVERAGE_SPEED);
        validatePassengers(passengersCount, passengersCapacity);

        this.averageSpeed = DefaultStats.CAR_AVERAGE_SPEED;
        this.fuelConsumption = fuelConsumption;
        this.passengersCapacity = passengersCapacity;
        this.passengersCount = passengersCount;
        this.fuel = carFuel;
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
