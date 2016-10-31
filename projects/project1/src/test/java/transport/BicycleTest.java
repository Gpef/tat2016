package transport;

import exceptions.WrongParameterException;
import org.junit.Before;
import org.junit.Test;
import route.Route;

import static org.junit.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class BicycleTest {

    private static double PRECISION_EPSILON = 1e-3;
    private Bicycle defaultBicycle;
    private RouteCreator routeCreator;

    @Before
    public void setUp() throws Exception {
        defaultBicycle = new Bicycle();
        routeCreator = new RouteCreator();
    }

    @Test
    public void calculateTime() throws Exception {
        Route route500km = routeCreator.createValid500km();
        Route route10000km = routeCreator.createValid10000km();
        assertEquals(31.25, defaultBicycle.calculateTime(route500km), PRECISION_EPSILON);
        assertEquals(625.0, defaultBicycle.calculateTime(route10000km), PRECISION_EPSILON);
    }

    @Test
    public void calculateCost() throws Exception {
        Route route500km = routeCreator.createValid500km();
        Route route10000km = routeCreator.createValid10000km();
        assertEquals(0.0, defaultBicycle.calculateCost(route500km), PRECISION_EPSILON);
        assertEquals(0.0, defaultBicycle.calculateCost(route10000km), PRECISION_EPSILON);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setSpeedLowerZero() throws Exception {
       defaultBicycle.setSpeed(-15);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setSpeedEqualsZero() throws Exception {
        defaultBicycle.setSpeed(0);
    }

    @Test
    public void setSpeedMoreThanZero() throws Exception {
        defaultBicycle.setSpeed(14);
        assertEquals(14, defaultBicycle.getSpeed(), PRECISION_EPSILON);
    }

    @Test(expected = WrongParameterException.class)
    public void setSpeedNaN() throws Exception {
        defaultBicycle.setSpeed(Double.NaN);
    }

    @Test(expected = WrongParameterException.class)
    public void setSpeedNegInfinity() throws Exception {
        defaultBicycle.setSpeed(Double.NEGATIVE_INFINITY);
    }

    @Test(expected = WrongParameterException.class)
    public void setSpeedPosInfinity() throws Exception {
        defaultBicycle.setSpeed(Double.POSITIVE_INFINITY);
    }
}