package route;

import exceptions.Messages;
import exceptions.RouteException;

import java.util.ArrayList;

/**
 * Represents route as array of checkpoints with their coordinates.
 * Can be created by reading checkpoint coordinate from file.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 25.10.2016
 */
public class Route {

    private ArrayList<Checkpoint> checkpoints;

    public Route(ArrayList<Checkpoint> checkpointsList) throws Exception {
        checkRouteValidness(checkpointsList);
        checkpoints = checkpointsList;
    }

    /**
     * Checks list of checkpoints to valid rules and throws
     * {@code RouteReaderException} if checkpoints list doesn't
     * correspond to any rule.
     *
     * @param checkpoints list of {@code Checkpoint} checkpoints
     *                    to check
     * @throws RouteException if at least one rule was broken
     */
    private void checkRouteValidness(ArrayList<Checkpoint> checkpoints) throws RouteException {
        if (checkpoints.size() == 0) {
            throw new RouteException(Messages.ERROR + " No checkpoints found");
        }
        if (checkpoints.size() == 1) {
            throw new RouteException(Messages.ERROR + " Route can't contain only one checkpoint");
        }
        if (checkpoints.get(0).equals(checkpoints.get(checkpoints.size() - 1))) {
            throw new RouteException(Messages.ERROR + " First and last checkpoints of route can't be equal");
        }
    }

    public ArrayList<Checkpoint> getCheckpoints() {
        return checkpoints;
    }
}
