package transport;

import exceptions.WrongParameterException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import route.Route;
import transport.fuel.Fuel;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class BusTest {

    private Bus defaultBus;

    @DataProvider(name = "invalidConstructor")
    public Object[][] getInvalidConstructorData() throws Exception {
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
    public Object[][] getValidConstructorData() throws Exception {
        return new Object[][]{
                {Fuel.DIESEL, 25d, 30, 40},
                {Fuel.DIESEL, 25d, 40, 40}
        };
    }

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
                {1e-3d}
        };
    }

    @DataProvider(name = "routeTime")
    public Object[][] getRouteTimeData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), 7.1428},
                {routeCreator.createValid10000km(), 142.8571},
        };
    }

    @DataProvider(name = "routeCost")
    public Object[][] getRouteCostData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), 1, 100d},
                {routeCreator.createValid500km(), 40, 2.5d},
                {routeCreator.createValid10000km(), 1, 2000d},
                {routeCreator.createValid10000km(), 40, 50d},
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
        defaultBus = new Bus(Fuel.DIESEL, 25, 40, 40);
    }

    @Test(dataProvider = "routeTime")
    public void calculateTime(Route route, double time) throws Exception {
        assertEquals(time, new Bus(Fuel.DIESEL, 25, 40, 40).calculateTime(route), 1e-3);
    }

    @Test(dataProvider = "routeCost")
    public void calculateCost(Route route, int passengerNumber, double cost) throws Exception {
        assertEquals(cost, new Bus(Fuel.DIESEL, 25, passengerNumber, passengerNumber).calculateCost(route));
    }

    @Test(dataProvider = "validConstructor")
    public void validConstructorData(Fuel fuel, double fuelConsumption,
                                     int passengersCount, int passengerCapacity) throws Exception {
        Bus bus = new Bus(fuel, fuelConsumption, passengersCount, passengerCapacity);
        assertEquals(passengersCount, bus.getPassengersCount());
        assertEquals(passengerCapacity, bus.getPassengersCapacity());
    }

    @Test(dataProvider = "invalidConstructor", expectedExceptions = WrongParameterException.class)
    public void invalidConstructorData(Fuel fuel, double fuelConsumption,
                                       int passengersCount, int passengerCapacity) throws Exception {
        new Bus(fuel, fuelConsumption, passengersCount, passengerCapacity);
    }

    @Test(dataProvider = "invalidFuelConsumption", expectedExceptions = WrongParameterException.class)
    public void setInvalidFuelConsumption(double fuelConsumption) throws Exception {
        defaultBus.setFuelConsumption(fuelConsumption);
    }

    @Test(dataProvider = "validFuelConsumption")
    public void setValidFuelConsumption(double fuelConsumption) throws Exception {
        defaultBus.setFuelConsumption(fuelConsumption);
        assertEquals(defaultBus.getFuelConsumption(), fuelConsumption);
    }

    @Test(dataProvider = "invalidPassengers", expectedExceptions = exceptions.WrongParameterException.class)
    public void setInvalidPassengersCount(int passengersCount) throws Exception {
        defaultBus.setPassengersCapacity(passengersCount);
    }

    @Test
    public void setPassengersCountEqualsCapacity() throws Exception {
        Bus bus = new Bus(Fuel.DIESEL, 25, 30, 40);
        bus.setPassengersCount(40);
        assertEquals(40, bus.getPassengersCount());
    }

    @Test
    public void setPassengersCountLowerCapacity() throws Exception {
        Bus bus = new Bus(Fuel.DIESEL, 25, 40, 40);
        bus.setPassengersCount(5);
        assertEquals(5, bus.getPassengersCount());
    }

    @Test(expectedExceptions = exceptions.WrongParameterException.class)
    public void setPassengersCountMoreCapacity() throws Exception {
        new Bus(Fuel.DIESEL, 25, 40, 40).setPassengersCount(50);
    }

    @Test(dataProvider = "invalidPassengers", expectedExceptions = exceptions.WrongParameterException.class)
    public void setInvalidPassengersCapacity(int passengersCapacity) throws Exception {
        defaultBus.setPassengersCapacity(passengersCapacity);
    }

    @Test
    public void setPassengersCapacityMorePassengersCount() throws Exception {
        Bus bus = new Bus(Fuel.DIESEL, 25, 40, 40);
        bus.setPassengersCapacity(50);
        assertEquals(50, bus.getPassengersCapacity());
    }

    @Test(expectedExceptions = exceptions.WrongParameterException.class)
    public void setPassengersCapacityLowerPassengersCount() throws Exception {
        new Bus(Fuel.DIESEL, 25, 40, 40).setPassengersCapacity(10);
    }

    @Test(dataProvider = "validSpeed")
    public void setValidSpeed(double speed) throws Exception {
        defaultBus.setSpeed(speed);
        assertEquals(speed, defaultBus.getSpeed());
    }

    @Test(dataProvider = "invalidSpeed", expectedExceptions = WrongParameterException.class)
    public void setInvalidSpeed(double speed) throws Exception {
        defaultBus.setSpeed(speed);
    }
}