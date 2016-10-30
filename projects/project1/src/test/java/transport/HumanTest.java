package transport;

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
    Human human;

    @Before
    public void setUp() throws Exception {
        human = new Human();
    }

    @Test
    public void calculateTime() throws Exception {
        Route route500km = RouteCreator.createValid500km();
        Route route10000km = RouteCreator.createValid10000km();
        assertEquals(16.6666, human.calculateTime(route500km), PRECISION_EPSILON);
        assertEquals(833.3333, human.calculateTime(route10000km), PRECISION_EPSILON);
    }

    @Test
    public void calculateCost() throws Exception {
        Route route500km = RouteCreator.createValid500km();
        Route route10000km = RouteCreator.createValid10000km();
        Assert.assertEquals(0.0, human.calculateCost(route500km), PRECISION_EPSILON);
        Assert.assertEquals(0.0, human.calculateCost(route10000km), PRECISION_EPSILON);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setSpeedEqualsZero() throws Exception {
        human.setSpeed(0);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setSpeedLowerZero() throws Exception {
        human.setSpeed(-1);
    }

    @Test
    public void setSpeedMoreThanZero() throws Exception {
        human.setSpeed(111);
    }

}