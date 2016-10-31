package transport;

import exceptions.WrongParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import route.Route;

import static org.junit.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 30.10.2016
 */
public class HumanTest {

    private static double PRECISION_EPSILON = 1e-3;
    private Human defaultHuman;
    private RouteCreator routeCreator;

    @Before
    public void setUp() throws Exception {
        defaultHuman = new Human();
        routeCreator = new RouteCreator();
    }

    @Test
    public void calculateTime() throws Exception {
        Route route500km = routeCreator.createValid500km();
        Route route10000km = routeCreator.createValid10000km();
        assertEquals(16.6666, defaultHuman.calculateTime(route500km), PRECISION_EPSILON);
        assertEquals(833.3333, defaultHuman.calculateTime(route10000km), PRECISION_EPSILON);
    }

    @Test
    public void calculateCost() throws Exception {
        Route route500km = routeCreator.createValid500km();
        Route route10000km = routeCreator.createValid10000km();
        Assert.assertEquals(0.0, defaultHuman.calculateCost(route500km), PRECISION_EPSILON);
        Assert.assertEquals(0.0, defaultHuman.calculateCost(route10000km), PRECISION_EPSILON);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setSpeedEqualsZero() throws Exception {
        defaultHuman.setSpeed(0);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setSpeedLowerZero() throws Exception {
        defaultHuman.setSpeed(-1);
    }

    @Test
    public void setSpeedMoreThanZero() throws Exception {
        defaultHuman.setSpeed(111);
    }
    @Test(expected = WrongParameterException.class)
    public void setSpeedNaN() throws Exception {
        defaultHuman.setSpeed(Double.NaN);
    }

    @Test(expected = WrongParameterException.class)
    public void setSpeedNegInfinity() throws Exception {
        defaultHuman.setSpeed(Double.NEGATIVE_INFINITY);
    }

    @Test(expected = WrongParameterException.class)
    public void setSpeedPosInfinity() throws Exception {
        defaultHuman.setSpeed(Double.POSITIVE_INFINITY);
    }

}