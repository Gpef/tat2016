package transport;

import exceptions.RoutePassingException;
import exceptions.WrongParameterException;
import route.Checkpoint;
import route.Route;
import route.RouteUtils;
import transport.base.Vehicle;

import java.util.ArrayList;

import static transport.DefaultStats.BICYCLE_AVERAGE_SPEED;

/**
 * Represents ordinary bicycle driving by physical
 * force of people.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 25.10.2016
 */
public class Bicycle extends Vehicle {

    /**
     * Creates new bicycle object. Performs parameters validating.
     * Also validates parameters from config class that
     * contains default parameters.
     *
     * @throws WrongParameterException if default bicycle speed has
     *                                 not valid value and {@code Bus} object
     *                                 can't be created
     */
    public Bicycle() throws WrongParameterException {
        validateSpeed(BICYCLE_AVERAGE_SPEED);

        this.averageSpeed = BICYCLE_AVERAGE_SPEED;
    }

    @Override
    public double calculateTime(Route route) throws RoutePassingException {
        double routeTime = 0;
        ArrayList<Checkpoint> points = route.getCheckpoints();
        RouteUtils routeUtils = new RouteUtils();
        for (int i = 1; i < points.size(); i++) {
            routeTime += routeUtils.calculateEuclidDistance(points.get(i - 1), points.get(i)) / getSpeed();
        }
        if (Double.isInfinite(routeTime)) {
            throw new RoutePassingException("Some variables has bad values, so passing time is infinite");
        }
        return routeTime;
    }
}
