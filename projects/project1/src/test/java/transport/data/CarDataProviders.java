package transport.data;

import org.testng.annotations.DataProvider;
import transport.RouteCreator;
import transport.fuel.Fuel;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 01.11.2016
 */
public abstract class CarDataProviders {

    @DataProvider(name = "invalidConstructor")
    public static Object[][] getInvalidConstructorData() throws Exception {
        return new Object[][]{
                {Fuel.PETROL, -15d, 1, 5},
                {Fuel.PETROL, 0d, 1, 5},
                {Fuel.PETROL, 5d, -15, 5},
                {Fuel.PETROL, 5d, 0, 5},
                {Fuel.PETROL, 5d, 1, -15},
                {Fuel.PETROL, 5d, 1, 0},
                {Fuel.PETROL, 5d, 15, 5},
        };
    }

    @DataProvider(name = "validConstructor")
    public static Object[][] getValidConstructorData() throws Exception {
        return new Object[][]{
                {Fuel.PETROL, 5d, 1, 5},
                {Fuel.PETROL, 5d, 5, 5}
        };
    }

    @DataProvider(name = "routeTime")
    public static Object[][] getRouteTimeData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), 5.5555},
                {routeCreator.createValid10000km(), 111.1111},
        };
    }

    @DataProvider(name = "routeCost")
    public static Object[][] getRouteCostData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), 1, 25d},
                {routeCreator.createValid500km(), 5, 5d},
                {routeCreator.createValid10000km(), 1, 500d},
                {routeCreator.createValid10000km(), 5, 100d},
        };
    }
}
