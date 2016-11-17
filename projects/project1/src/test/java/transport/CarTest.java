package transport;

import exceptions.WrongParameterException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import route.Route;
import transport.data.CarDataProviders;
import transport.data.TransportParamsDataProviders;
import transport.fuel.Fuel;

import static org.testng.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class CarTest {

    private Car defaultCar;

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

    @Test(dataProvider = "routeTime", dataProviderClass = CarDataProviders.class)
    public void calculateTime(Route route, double time) throws Exception {
        assertEquals(time, new Car(Fuel.PETROL, 5, 1, 5).calculateTime(route), 1e-3);
    }

    @Test(dataProvider = "routeCost", dataProviderClass = CarDataProviders.class)
    public void calculateCost(Route route, int passengerNumber, double cost) throws Exception {
        assertEquals(cost, new Car(Fuel.PETROL, 5, passengerNumber, passengerNumber).calculateCost(route));
    }

    @Test(dataProvider = "validConstructor", dataProviderClass = CarDataProviders.class)
    public void validConstructorData(Fuel fuel, double fuelConsumption,
                                     int passengersCount, int passengerCapacity) throws Exception {
        Car car = new Car(fuel, fuelConsumption, passengersCount, passengerCapacity);
        assertEquals(passengersCount, car.getPassengersCount());
        assertEquals(passengerCapacity, car.getPassengersCapacity());
    }

    @Test(dataProvider = "invalidConstructor", dataProviderClass = CarDataProviders.class
            , expectedExceptions = WrongParameterException.class)
    public void invalidConstructorData(Fuel fuel, double fuelConsumption,
                                       int passengersCount, int passengerCapacity) throws Exception {
        new Car(fuel, fuelConsumption, passengersCount, passengerCapacity);
    }

    @Test(dataProvider = "invalidFuelConsumption", dataProviderClass = TransportParamsDataProviders.class,
            expectedExceptions = WrongParameterException.class)
    public void setInvalidFuelConsumption(double fuelConsumption) throws Exception {
        defaultCar.setFuelConsumption(fuelConsumption);
    }

    @Test(dataProvider = "validFuelConsumption", dataProviderClass = TransportParamsDataProviders.class)
    public void setValidFuelConsumption(double fuelConsumption) throws Exception {
        defaultCar.setFuelConsumption(fuelConsumption);
        assertEquals(defaultCar.getFuelConsumption(), fuelConsumption);
    }

    @Test
    public void setPassengersCountEqualsCapacity() throws Exception {
        Car car = new Car(Fuel.PETROL, 5, 1, 5);
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

    @Test(dataProvider = "validSpeed", dataProviderClass = TransportParamsDataProviders.class)
    public void setValidSpeed(double speed) throws Exception {
        defaultCar.setSpeed(speed);
        assertEquals(speed, defaultCar.getSpeed());
    }

    @Test(dataProvider = "invalidSpeed", dataProviderClass = TransportParamsDataProviders.class,
            expectedExceptions = WrongParameterException.class)
    public void setInvalidSpeed(double speed) throws Exception {
        defaultCar.setSpeed(speed);
    }
}