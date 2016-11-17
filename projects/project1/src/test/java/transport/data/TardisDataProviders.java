package transport.data;

import org.testng.annotations.DataProvider;
import transport.RouteCreator;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 01.11.2016
 */
public abstract class TardisDataProviders {

    private static double DEFAULT_MOVING_TIME = 5.5e-4;

    @DataProvider(name = "invalidConstructor")
    public static Object[][] getInvalidConstructorData() throws Exception {
        return new Object[][]{
                {-15d, 1, 1},
                {0d, 1, 1},
                {10d, -15, 1},
                {10d, 0, 1},
                {10d, 1, -15},
                {10d, 1, 0},
                {10d, 5, 1},
        };
    }

    @DataProvider(name = "validConstructor")
    public static Object[][] getValidConstructorData() throws Exception {
        return new Object[][]{
                {10d, 1, 2},
                {10d, 1, 1}
        };
    }

    @DataProvider(name = "routeTime")
    public static Object[][] getRouteTimeData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), DEFAULT_MOVING_TIME},
                {routeCreator.createValid10000km(), DEFAULT_MOVING_TIME},
        };
    }

    @DataProvider(name = "routeCost")
    public static Object[][] getRouteCostData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), 1, 50d},
                {routeCreator.createValid500km(), 10, 5d},
                {routeCreator.createValid10000km(), 1, 1000d},
                {routeCreator.createValid10000km(), 10, 100d},
        };
    }
}
