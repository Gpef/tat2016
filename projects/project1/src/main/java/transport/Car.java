package transport;

import transport.fuel.Fuel;
import route.Checkpoint;
import route.Route;

import java.util.ArrayList;

import static route.RouteUtils.calculateDistance;
import static route.RouteUtils.calculateRouteLength;

/**
 * Represents common car that moving fast.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 25.10.2016
 */
public class Car extends MotorVehicle {

    protected double passengersCount;

    public Car(Fuel carFuel, double carFuelConsumption, int carPassengers) {
        averageSpeed = 90;
        fuelConsumption = carFuelConsumption;
        passengersCount = carPassengers;
        fuel = carFuel;
    }

    public double getSpeed() {
        return averageSpeed;
    }

    @Override
    public double calculateTime(Route route) {
        double routeTime = 0;
        ArrayList<Checkpoint> points = route.getCheckpoints();
        for (int i = 1; i < points.size() - 1; i++) {
            routeTime += calculateDistance(points.get(i - 1), points.get(i)) / getSpeed();
        }
        return routeTime;
    }

    @Override
    public double calculateCost(Route route) {
        return fuelConsumption / 100 * calculateRouteLength(route) * fuel.getPrice() / passengersCount;
    }
}
