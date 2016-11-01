package transport.data;

import org.testng.annotations.DataProvider;
import transport.RouteCreator;
import transport.fuel.Fuel;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 01.11.2016
 */
public abstract class BusDataProviders {

    @DataProvider(name = "invalidConstructor")
    public static Object[][] getInvalidConstructorData() throws Exception {
        return new Object[][]{
                {Fuel.DIESEL, -15d, 40, 40},
                {Fuel.DIESEL, 0d, 40, 40},
                {Fuel.DIESEL, 25d, -15, 40},
                {Fuel.DIESEL, 25d, 0, 40},
                {Fuel.DIESEL, 25d, 40, -15},
                {Fuel.DIESEL, 25d, 40, 0},
                {Fuel.DIESEL, 25d, 50, 40},
        };
    }

    @DataProvider(name = "validConstructor")
    public static Object[][] getValidConstructorData() throws Exception {
        return new Object[][]{
                {Fuel.DIESEL, 25d, 30, 40},
                {Fuel.DIESEL, 25d, 40, 40}
        };
    }

    @DataProvider(name = "routeTime")
    public static Object[][] getRouteTimeData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), 7.1428},
                {routeCreator.createValid10000km(), 142.8571},
        };
    }

    @DataProvider(name = "routeCost")
    public static Object[][] getRouteCostData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), 1, 100d},
                {routeCreator.createValid500km(), 40, 2.5d},
                {routeCreator.createValid10000km(), 1, 2000d},
                {routeCreator.createValid10000km(), 40, 50d},
        };
    }
}

