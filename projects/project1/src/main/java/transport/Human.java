package transport;

import exceptions.WrongParameterException;
import route.Checkpoint;
import route.Route;
import route.RouteUtils;
import transport.base.CanPassRoute;

import java.util.ArrayList;

import static transport.DefaultStats.HUMAN_AVERAGE_SPEED;

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

    public Human() throws WrongParameterException {
        validateSpeed(HUMAN_AVERAGE_SPEED);
        this.averageSpeed = HUMAN_AVERAGE_SPEED;
    }

    public double getSpeed() {
        return this.averageSpeed;
    }

    /**
     * Sets new speed to vehicle. Throws {@code WrongParameterException}
     * if new speed parameter isn't valid.
     *
     * @param newSpeed new speed to set
     * @throws WrongParameterException if newSpeed <= 0
     */
    public void setSpeed(double newSpeed) throws WrongParameterException {
        validateSpeed(newSpeed);
        this.averageSpeed = newSpeed;
    }

    /**
     * Validates speed parameter. If it's wrong
     * throws {@code WrongParameterException}.
     *
     * @param validateSpeed speed parameter to validate
     * @throws WrongParameterException if new speed is <= 0
     */
    protected void validateSpeed(double validateSpeed) throws WrongParameterException {
        if (Double.compare(validateSpeed, 0) <= 0) {
            throw new WrongParameterException("Error creating new " + this.getClass().getSimpleName() + ": speed can't be <= 0");
        }
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
        return 0;
    }
}
