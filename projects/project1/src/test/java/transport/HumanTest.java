package transport;

import exceptions.WrongParameterException;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import route.Route;

import static org.testng.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 30.10.2016
 */
public class HumanTest {

    private Human defaultHuman;

    @DataProvider(name = "invalidSpeed")
    public Object[][] getInvalidSpeedData() throws Exception {
        return new Object[][]{
                {Double.NaN},
                {Double.POSITIVE_INFINITY},
                {Double.NEGATIVE_INFINITY},
                {0d},
                {-10d},
        };
    }

    @DataProvider(name = "validSpeed")
    public Object[][] getValidSpeedData() throws Exception {
        return new Object[][]{
                {1d},
                {Double.MIN_VALUE},
                {Double.MAX_VALUE}
        };
    }

    @DataProvider(name = "validRouteTime")
    public Object[][] getValidRouteTimeData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), 16.666},
                {routeCreator.createValid10000km(), 833.333},
        };
    }

    @BeforeMethod
    public void setUp() throws Exception {
        defaultHuman = new Human();
    }

    @Test (dataProvider = "validRouteTime")
    public void calculateTime(Route route, double time) throws Exception {
        assertEquals(defaultHuman.calculateTime(route), time, 1e-3);
    }

    @Test(dataProvider = "validSpeed")
    public void setValidSpeed(double speed) throws Exception {
        defaultHuman.setSpeed(speed);
        AssertJUnit.assertEquals(speed, defaultHuman.getSpeed());
    }

    @Test(dataProvider = "invalidSpeed", expectedExceptions = WrongParameterException.class)
    public void setInvalidSpeed(double speed) throws Exception {
        defaultHuman.setSpeed(speed);
    }

}