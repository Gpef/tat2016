package transport;

import exceptions.WrongParameterException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import route.Route;
import transport.data.TardisDataProviders;
import transport.data.TransportParamsDataProviders;

import static org.testng.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 30.10.2016
 */
public class TardisTest {

    private Tardis defaultTardis;

    @BeforeMethod
    public void setUp() throws Exception {
        defaultTardis = new Tardis(10, 1, 1);
    }

    @Test(dataProvider = "routeTime", dataProviderClass = TardisDataProviders.class)
    public void calculateTime(Route route, double time) throws Exception {
        assertEquals(time, new Tardis(10, 1, 1).calculateTime(route), 1e-3);
    }

    @Test(dataProvider = "routeCost", dataProviderClass = TardisDataProviders.class)
    public void calculateCost(Route route, int passengerNumber, double cost) throws Exception {
        assertEquals(cost, new Tardis(10, passengerNumber, passengerNumber).calculateCost(route), 1e-3);
    }

    @Test(dataProvider = "validConstructor", dataProviderClass = TardisDataProviders.class)
    public void validConstructorData(double fuelConsumption,
                                     int passengersCount, int passengerCapacity) throws Exception {
        Tardis car = new Tardis(fuelConsumption, passengersCount, passengerCapacity);
        assertEquals(passengersCount, car.getPassengersCount());
        assertEquals(passengerCapacity, car.getPassengersCapacity());
    }

    @Test(dataProvider = "invalidConstructor", dataProviderClass = TardisDataProviders.class,
            expectedExceptions = WrongParameterException.class)
    public void invalidConstructorData(double fuelConsumption,
                                       int passengersCount, int passengerCapacity) throws Exception {
        new Tardis(fuelConsumption, passengersCount, passengerCapacity);
    }

    @Test(dataProvider = "invalidFuelConsumption", dataProviderClass = TransportParamsDataProviders.class,
            expectedExceptions = WrongParameterException.class)
    public void setInvalidFuelConsumption(double fuelConsumption) throws Exception {
        defaultTardis.setFuelConsumption(fuelConsumption);
    }

    @Test(dataProvider = "validFuelConsumption", dataProviderClass = TransportParamsDataProviders.class)
    public void setValidFuelConsumption(double fuelConsumption) throws Exception {
        defaultTardis.setFuelConsumption(fuelConsumption);
        assertEquals(defaultTardis.getFuelConsumption(), fuelConsumption);
    }

    @Test(dataProvider = "invalidPassengers", dataProviderClass = TransportParamsDataProviders.class,
            expectedExceptions = exceptions.WrongParameterException.class)
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

    @Test(dataProvider = "invalidPassengers", dataProviderClass = TransportParamsDataProviders.class,
            expectedExceptions = exceptions.WrongParameterException.class)
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