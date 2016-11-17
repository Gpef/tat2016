package route;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 27.10.2016
 */
public class RouteUtilsTest {

    private static double PRECISION_EPSILON = 1e-3;
    private static RouteUtils routeUtils;

    @BeforeMethod
    public void setUp() {
        routeUtils = new RouteUtils();
    }

    @Test
    public void calculateDistance() throws Exception {
        Checkpoint point1 = new Checkpoint(1.0, 1.0);
        Checkpoint point2 = new Checkpoint(1000.0, 1000.0);
        assertEquals(1412.7993, routeUtils.calculateEuclidDistance(point1, point2), PRECISION_EPSILON);
    }

    @Test
    public void calculateRouteLength3Points() throws Exception {
        ArrayList<Checkpoint> checkpoints = new ArrayList<>();
        checkpoints.add(new Checkpoint(0, 0));
        checkpoints.add(new Checkpoint(10, 0));
        checkpoints.add(new Checkpoint(100, 0));
        Route route = new Route(checkpoints);
        assertEquals(100, routeUtils.calculateEuclidRouteLength(route), PRECISION_EPSILON);
    }
}