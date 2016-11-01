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
 * @since 30.10.2016
 */
public class TardisTest {

    private static double DEFAULT_MOVING_TIME = 5.5e-4;
    private Tardis defaultTardis;

    @DataProvider(name = "invalidConstructor")
    public Object[][] getInvalidConstructorData() throws Exception {
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
    public Object[][] getValidConstructorData() throws Exception {
        return new Object[][]{
                {10d, 1, 2},
                {10d, 1, 1}
        };
    }

    @DataProvider(name = "invalidFuelConsumption")
    public Object[][] getInvalidFuelConsumptionData() throws Exception {
        return new Object[][]{
                {Double.NaN},
                {Double.POSITIVE_INFINITY},
                {Double.NEGATIVE_INFINITY},
                {0d},
                {-100d},
                {-1e-300d}
        };
    }

    @DataProvider(name = "validFuelConsumption")
    public Object[][] getValidFuelConsumptionData() throws Exception {
        return new Object[][]{
                {Double.MAX_VALUE},
                {Double.MIN_VALUE},
                {1e-300d}
        };
    }

    @DataProvider(name = "routeTime")
    public Object[][] getRouteTimeData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), DEFAULT_MOVING_TIME},
                {routeCreator.createValid10000km(), DEFAULT_MOVING_TIME},
        };
    }

    @DataProvider(name = "routeCost")
    public Object[][] getRouteCostData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), 1, 50d},
                {routeCreator.createValid500km(), 10, 5d},
                {routeCreator.createValid10000km(), 1, 1000d},
                {routeCreator.createValid10000km(), 10, 100d},
        };
    }

    @DataProvider(name = "invalidPassengers")
    public Object[][] getInvalidPassengersData() throws Exception {
        return new Object[][]{
                {-100},
                {0},
                {Integer.MIN_VALUE}
        };
    }

    @BeforeMethod
    public void setUp() throws Exception {
        defaultTardis = new Tardis(10, 1, 1);
    }

    @Test(dataProvider = "routeTime")
    public void calculateTime(Route route, double time) throws Exception {
        assertEquals(time, new Tardis(10, 1, 1).calculateTime(route), 1e-3);
    }

    @Test(dataProvider = "routeCost")
    public void calculateCost(Route route, int passengerNumber, double cost) throws Exception {
        assertEquals(cost, new Tardis(10, passengerNumber, passengerNumber).calculateCost(route), 1e-3);
    }

    @Test(dataProvider = "validConstructor")
    public void validConstructorData(double fuelConsumption,
                                     int passengersCount, int passengerCapacity) throws Exception {
        Tardis car = new Tardis(fuelConsumption, passengersCount, passengerCapacity);
        assertEquals(passengersCount, car.getPassengersCount());
        assertEquals(passengerCapacity, car.getPassengersCapacity());
    }

    @Test(dataProvider = "invalidConstructor", expectedExceptions = WrongParameterException.class)
    public void invalidConstructorData(double fuelConsumption,
                                       int passengersCount, int passengerCapacity) throws Exception {
        new Tardis(fuelConsumption, passengersCount, passengerCapacity);
    }

    @Test(dataProvider = "invalidFuelConsumption", expectedExceptions = WrongParameterException.class)
    public void setInvalidFuelConsumption(double fuelConsumption) throws Exception {
        defaultTardis.setFuelConsumption(fuelConsumption);
    }

    @Test(dataProvider = "validFuelConsumption")
    public void setValidFuelConsumption(double fuelConsumption) throws Exception {
        defaultTardis.setFuelConsumption(fuelConsumption);
        assertEquals(defaultTardis.getFuelConsumption(), fuelConsumption);
    }

    @Test(dataProvider = "invalidPassengers", expectedExceptions = exceptions.WrongParameterException.class)
    public void setInvalidPassengersCount(int passengersCount) throws Exception {
        defaultTardis.setPassengersCapacity(passengersCount);
    }

    @Test
    public void setPassengersCountEqualsCapacity() throws Exception {
        Tardis tardis = new Tardis(10, 1, 5);
        tardis.setPassengersCount(5);
        assertEquals(5, tardis.getPassengersCount());
    }

    @Test
    public void setPassengersCountLowerCapacity() throws Exception {
        Tardis tardis = new Tardis(10, 1, 5);
        tardis.setPassengersCount(2);
        assertEquals(2, tardis.getPassengersCount());
    }

    @Test(expectedExceptions = exceptions.WrongParameterException.class)
    public void setPassengersCountMoreCapacity() throws Exception {
        new Tardis(10, 1, 1).setPassengersCount(5);
    }

    @Test(dataProvider = "invalidPassengers", expectedExceptions = exceptions.WrongParameterException.class)
    public void setInvalidPassengersCapacity(int passengersCapacity) throws Exception {
        defaultTardis.setPassengersCapacity(passengersCapacity);
    }

    @Test
    public void setPassengersCapacityMorePassengersCount() throws Exception {
        Tardis car = new Tardis(10, 1, 1);
        car.setPassengersCapacity(6);
        assertEquals(6, car.getPassengersCapacity());
    }

    @Test(expectedExceptions = exceptions.WrongParameterException.class)
    public void setPassengersCapacityLowerPassengersCount() throws Exception {
        new Tardis(10, 5, 5).setPassengersCapacity(1);
    }
}