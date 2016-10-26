package transport;

import route.Checkpoint;
import route.Route;
import transport.base.CanPassRoute;

import java.util.ArrayList;

import static route.RouteUtils.calculateDistance;

/**
 * Ordinary human. Moving very slowly, but doesn't
 * consume fuel.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 25.10.2016
 */
public class Human implements CanPassRoute {

    private double averageSpeed;

    public Human() {
        averageSpeed = 6;
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
