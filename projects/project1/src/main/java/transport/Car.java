package transport;

import route.Checkpoint;
import route.Route;
import route.RouteUtils;
import transport.base.MotorVehicle;
import transport.fuel.Fuel;

import java.util.ArrayList;

import static transport.DefaultStats.CAR_AVERAGE_SPEED;

/**
 * Represents common car that is moving fast and has little
 * fuel consumption.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 25.10.2016
 */
public class Car extends MotorVehicle {

    private double passengersCount;

    public Car(Fuel carFuel, double carFuelConsumption, int carPassengers) {
        averageSpeed = CAR_AVERAGE_SPEED;
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
            routeTime += new RouteUtils().calculateEuclidDistance(points.get(i - 1), points.get(i)) / getSpeed();
        }
        return routeTime;
    }

    @Override
    public double calculateCost(Route route) {
        return fuelConsumption / 100 * new RouteUtils().calculateEuclidRouteLength(route) * fuel.getPrice() / passengersCount;
    }
}
