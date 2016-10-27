package transport;

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

    protected double passengersCount;

    public Bus(Fuel busFuel, double busFuelConsumption, int busPassengers) {
        averageSpeed = BUS_AVERAGE_SPEED;
        fuel = busFuel;
        fuelConsumption = busFuelConsumption;
        passengersCount = busPassengers;
    }

    public double getSpeed() {
        return averageSpeed;
    }

    @Override
    public double calculateTime(Route route) {
        double routeTime = 0;
        ArrayList<Checkpoint> points = route.getCheckpoints();
        for (int i = 1; i < points.size() - 1; i++) {
            routeTime += new RouteUtils().calculateEuclidDistance(points.get(i - 1), points.get(i)) / getSpeed();
        }
        return routeTime;
    }

    @Override
    public double calculateCost(Route route) {
        return fuelConsumption / 100 * new RouteUtils().calculateEuclidRouteLength(route) * fuel.getPrice() / passengersCount;
    }
}
