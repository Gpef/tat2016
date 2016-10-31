package transport;

import exceptions.RouteException;
import exceptions.WrongParameterException;
import route.Checkpoint;
import route.Route;

import java.util.ArrayList;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 30.10.2016
 */
public final class RouteCreator {

    /**
     * Creates valid route500km length 500km with 3 points:
     * (1, 0) , (1, 100) and (1, 500).
     * It's made static to prevent making many {@code RouteCreator}
     * objects while testing.
     *
     * @return valid route500km with length 500km and 3 points
     * @throws RouteException if error occurred while creating
     *                        new route500km
     */
    public Route createValid500km() throws RouteException, WrongParameterException {
        ArrayList<Checkpoint> checkpoints = new ArrayList<>();
        checkpoints.add(new Checkpoint(1, 0));
        checkpoints.add(new Checkpoint(1, 100));
        checkpoints.add(new Checkpoint(1, 500));
        return new Route(checkpoints);
    }

    /**
     * Creates valid route500km length 10000km with 6 points:
     * (1, 0) , (1, 100), (1, 500), (1, 1000), (1, 5000) and (1, 10000).
     * It's made static to prevent making many {@code RouteCreator}
     * objects while testing.
     *
     * @return valid route500km with length 10000km and 6 points
     * @throws RouteException if error occurred while creating
     *                        new route500km
     */
    public Route createValid10000km() throws RouteException, WrongParameterException {
        ArrayList<Checkpoint> checkpoints = new ArrayList<>();
        checkpoints.add(new Checkpoint(1, 0));
        checkpoints.add(new Checkpoint(1, 100));
        checkpoints.add(new Checkpoint(1, 500));
        checkpoints.add(new Checkpoint(1, 1000));
        checkpoints.add(new Checkpoint(1, 5000));
        checkpoints.add(new Checkpoint(1, 10000));
        return new Route(checkpoints);
    }

}
