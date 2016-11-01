package transport;

import exceptions.WrongParameterException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import route.Route;
import transport.fuel.Fuel;

import static org.testng.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class CarTest {

    private Car defaultCar;

    @DataProvider(name = "invalidConstructor")
    public Object[][] getInvalidConstructorData() throws Exception {
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
    public Object[][] getValidConstructorData() throws Exception {
        return new Object[][]{
                {Fuel.PETROL, 5d, 1, 5},
                {Fuel.PETROL, 5d, 5, 5}
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
                {1e-300d}
        };
    }

    @DataProvider(name = "routeTime")
    public Object[][] getRouteTimeData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), 5.5555},
                {routeCreator.createValid10000km(), 111.1111},
        };
    }

    @DataProvider(name = "routeCost")
    public Object[][] getRouteCostData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), 1, 25d},
                {routeCreator.createValid500km(), 5, 5d},
                {routeCreator.createValid10000km(), 1, 500d},
                {routeCreator.createValid10000km(), 5, 100d},
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
        defaultCar = new Car(Fuel.PETROL, 5, 1, 5);
    }

    @Test(dataProvider = "routeTime")
    public void calculateTime(Route route, double time) throws Exception {
        assertEquals(time, new Car(Fuel.PETROL, 5, 1, 5).calculateTime(route), 1e-3);
    }

    @Test(dataProvider = "routeCost")
    public void calculateCost(Route route, int passengerNumber, double cost) throws Exception {
        assertEquals(cost, new Car(Fuel.PETROL, 5, passengerNumber, passengerNumber).calculateCost(route));
    }

    @Test(dataProvider = "validConstructor")
    public void validConstructorData(Fuel fuel, double fuelConsumption,
                                     int passengersCount, int passengerCapacity) throws Exception {
        Car car = new Car(fuel, fuelConsumption, passengersCount, passengerCapacity);
        assertEquals(passengersCount, car.getPassengersCount());
        assertEquals(passengerCapacity, car.getPassengersCapacity());
    }

    @Test(dataProvider = "invalidConstructor", expectedExceptions = WrongParameterException.class)
    public void invalidConstructorData(Fuel fuel, double fuelConsumption,
                                       int passengersCount, int passengerCapacity) throws Exception {
        new Car(fuel, fuelConsumption, passengersCount, passengerCapacity);
    }

    @Test(dataProvider = "invalidFuelConsumption", expectedExceptions = WrongParameterException.class)
    public void setInvalidFuelConsumption(double fuelConsumption) throws Exception {
        defaultCar.setFuelConsumption(fuelConsumption);
    }

    @Test(dataProvider = "validFuelConsumption")
    public void setValidFuelConsumption(double fuelConsumption) throws Exception {
        defaultCar.setFuelConsumption(fuelConsumption);
        assertEquals(defaultCar.getFuelConsumption(), fuelConsumption);
    }

    @Test
    public void setPassengersCountEqualsCapacity() throws Exception {
        Car car = new Car(Fuel.PETROL,5, 1, 5);
        car.setPassengersCount(5);
        assertEquals(5, car.getPassengersCount());
    }

    @Test(dataProvider = "invalidPassengers", expectedExceptions = exceptions.WrongParameterException.class)
    public void setInvalidPassengersCount(int passengersCount) throws Exception {
        defaultCar.setPassengersCapacity(passengersCount);
    }

    @Test
    public void setPassengersCountLowerCapacity() throws Exception {
        Car car = new Car(Fuel.PETROL, 5, 1, 5);
        car.setPassengersCount(3);
        assertEquals(3, car.getPassengersCount());
    }

    @Test(expectedExceptions = exceptions.WrongParameterException.class)
    public void setPassengersCountMoreCapacity() throws Exception {
        new Car(Fuel.PETROL, 5, 1, 5).setPassengersCount(10);
    }

    @Test(dataProvider = "invalidPassengers", expectedExceptions = exceptions.WrongParameterException.class)
    public void setInvalidPassengersCapacity(int passengersCapacity) throws Exception {
        defaultCar.setPassengersCapacity(passengersCapacity);
    }

    @Test
    public void setPassengersCapacityMorePassengersCount() throws Exception {
        Car car = new Car(Fuel.PETROL, 5, 1, 5);
        car.setPassengersCapacity(6);
        assertEquals(6, car.getPassengersCapacity());
    }

    @Test(expectedExceptions = exceptions.WrongParameterException.class)
    public void setPassengersCapacityLowerPassengersCount() throws Exception {
        new Car(Fuel.PETROL, 5, 5, 5).setPassengersCapacity(2);
    }

    @Test(dataProvider = "validSpeed")
    public void setValidSpeed(double speed) throws Exception {
        defaultCar.setSpeed(speed);
        assertEquals(speed, defaultCar.getSpeed());
    }

    @Test(dataProvider = "invalidSpeed", expectedExceptions = WrongParameterException.class)
    public void setInvalidSpeed(double speed) throws Exception {
        defaultCar.setSpeed(speed);
    }
}