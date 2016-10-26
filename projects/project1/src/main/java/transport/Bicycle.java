package transport;

import route.Checkpoint;
import route.Route;
import transport.base.PhysicalVehicle;

import java.util.ArrayList;

import static route.RouteUtils.calculateDistance;

/**
 * Represents ordinary bicycle driving by physical
 * force of people.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 25.10.2016
 */
public class Bicycle extends PhysicalVehicle {

    public Bicycle() {
        averageSpeed = 16;
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
        return 0;
    }
}
