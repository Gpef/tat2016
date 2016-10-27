package route;

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

    public Route(ArrayList<Checkpoint> checkpointsList) {
        checkpoints = checkpointsList;
    }

    public ArrayList<Checkpoint> getCheckpoints() {
        return checkpoints;
    }
}
