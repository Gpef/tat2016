package route;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class RouteTest {

    @Test(expected = exceptions.RouteException.class)
    public void routeWithOnePoint() throws Exception {
        ArrayList<Checkpoint> checkpoints = new ArrayList<>();
        Checkpoint point = new Checkpoint(1, 1);
        checkpoints.add(point);
        new Route(checkpoints);
    }

    @Test(expected = exceptions.RouteException.class)
    public void routeWithSameFirstAndLastCheckPoints() throws Exception {
        ArrayList<Checkpoint> checkpoints = new ArrayList<>();
        checkpoints.add(new Checkpoint(1, 1));
        checkpoints.add(new Checkpoint(1, 2));
        checkpoints.add(new Checkpoint(1, 3));
        checkpoints.add(new Checkpoint(1, 1));
        new Route(checkpoints);
    }

    @Test(expected = exceptions.RouteException.class)
    public void routeWithTwoSameCheckPoints() throws Exception {
        ArrayList<Checkpoint> checkpoints = new ArrayList<>();
        Checkpoint point1 = new Checkpoint(1, 1);
        checkpoints.add(point1);
        checkpoints.add(point1);
        new Route(checkpoints);
    }

    @Test(expected = exceptions.RouteException.class)
    public void routeWithEmptyCheckpoints() throws Exception {
        ArrayList<Checkpoint> checkpoints = new ArrayList<>();
        new Route(checkpoints);
    }

}
