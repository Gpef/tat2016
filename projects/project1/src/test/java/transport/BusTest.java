package transport;

import exceptions.WrongParameterException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import route.Route;
import transport.data.BusDataProviders;
import transport.data.TransportParamsDataProviders;
import transport.fuel.Fuel;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class BusTest {

    private Bus defaultBus;

    @BeforeMethod
    public void setUp() throws Exception {
        defaultBus = new Bus(Fuel.DIESEL, 25, 40, 40);
    }

    @Test(dataProvider = "routeTime", dataProviderClass = BusDataProviders.class)
    public void calculateTime(Route route, double time) throws Exception {
        assertEquals(time, new Bus(Fuel.DIESEL, 25, 40, 40).calculateTime(route), 1e-3);
    }

    @Test(dataProvider = "routeCost", dataProviderClass = BusDataProviders.class)
    public void calculateCost(Route route, int passengerNumber, double cost) throws Exception {
        assertEquals(cost, new Bus(Fuel.DIESEL, 25, passengerNumber, passengerNumber).calculateCost(route));
    }

    @Test(dataProvider = "validConstructor", dataProviderClass = BusDataProviders.class)
    public void validConstructorData(Fuel fuel, double fuelConsumption,
                                     int passengersCount, int passengerCapacity) throws Exception {
        Bus bus = new Bus(fuel, fuelConsumption, passengersCount, passengerCapacity);
        assertEquals(passengersCount, bus.getPassengersCount());
        assertEquals(passengerCapacity, bus.getPassengersCapacity());
    }

    @Test(dataProvider = "invalidConstructor", dataProviderClass = BusDataProviders.class,
            expectedExceptions = WrongParameterException.class)
    public void invalidConstructorData(Fuel fuel, double fuelConsumption,
                                       int passengersCount, int passengerCapacity) throws Exception {
        new Bus(fuel, fuelConsumption, passengersCount, passengerCapacity);
    }

    @Test(dataProvider = "invalidFuelConsumption", dataProviderClass = TransportParamsDataProviders.class,
            expectedExceptions = WrongParameterException.class)
    public void setInvalidFuelConsumption(double fuelConsumption) throws Exception {
        defaultBus.setFuelConsumption(fuelConsumption);
    }

    @Test(dataProvider = "validFuelConsumption", dataProviderClass = TransportParamsDataProviders.class)
    public void setValidFuelConsumption(double fuelConsumption) throws Exception {
        defaultBus.setFuelConsumption(fuelConsumption);
        assertEquals(defaultBus.getFuelConsumption(), fuelConsumption);
    }

    @Test(dataProvider = "invalidPassengers", dataProviderClass = TransportParamsDataProviders.class,
            expectedExceptions = exceptions.WrongParameterException.class)
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

    @Test(dataProvider = "invalidPassengers", dataProviderClass = TransportParamsDataProviders.class,
            expectedExceptions = exceptions.WrongParameterException.class)
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

    @Test(dataProvider = "validSpeed", dataProviderClass = TransportParamsDataProviders.class)
    public void setValidSpeed(double speed) throws Exception {
        defaultBus.setSpeed(speed);
        assertEquals(speed, defaultBus.getSpeed());
    }

    @Test(dataProvider = "invalidSpeed", dataProviderClass = TransportParamsDataProviders.class,
            expectedExceptions = WrongParameterException.class)
    public void setInvalidSpeed(double speed) throws Exception {
        defaultBus.setSpeed(speed);
    }
}