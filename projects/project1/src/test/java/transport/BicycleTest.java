package transport;

import exceptions.WrongParameterException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import route.Route;

import static org.testng.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class BicycleTest {

    private Bicycle defaultBicycle;

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
                {routeCreator.createValid500km(), 31.25},
                {routeCreator.createValid10000km(), 625.0},
        };
    }

    @BeforeMethod
    public void setUp() throws Exception {
        defaultBicycle = new Bicycle();
    }

    @Test (dataProvider = "validRouteTime")
    public void calculateTime(Route route, double time) throws Exception {
        assertEquals(defaultBicycle.calculateTime(route), time, 1e-3);
    }

    @Test(dataProvider = "invalidSpeed", expectedExceptions = WrongParameterException.class)
    public void setInvalidSpeed(double speed) throws Exception {
        defaultBicycle.setSpeed(speed);
    }

    @Test(dataProvider = "validSpeed")
    public void setValidSpeed(double speed) throws Exception {
        defaultBicycle.setSpeed(speed);
        assertEquals(defaultBicycle.getSpeed(), speed);
    }

}