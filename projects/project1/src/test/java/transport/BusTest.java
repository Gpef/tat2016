package transport;

import exceptions.WrongParameterException;
import org.junit.Before;
import org.junit.Test;
import route.Route;
import transport.fuel.Fuel;

import static org.junit.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class BusTest {

    private static double PRECISION_EPSILON = 1e-3;
    private Bus defaultBus;
    private RouteCreator routeCreator;

    @Before
    public void setUp() throws Exception {
        defaultBus = new Bus(Fuel.DIESEL, 25, 40, 40);
        routeCreator = new RouteCreator();
    }

    @Test
    public void calculateTime() throws Exception {
        Route route500km = routeCreator.createValid500km();
        Route route10000km = routeCreator.createValid10000km();
        assertEquals(7.1428, new Bus(Fuel.DIESEL, 25, 40, 40).calculateTime(route500km), PRECISION_EPSILON);
        assertEquals(142.8571, new Bus(Fuel.DIESEL, 25, 40, 40).calculateTime(route10000km), PRECISION_EPSILON);
    }

    @Test
    public void calculateCost1Passenger() throws Exception {
        Route route500km = routeCreator.createValid500km();
        Route route10000km = routeCreator.createValid10000km();
        assertEquals(100.0, new Bus(Fuel.DIESEL, 25, 1, 40).calculateCost(route500km), PRECISION_EPSILON);
        assertEquals(2000.0, new Bus(Fuel.DIESEL, 25, 1, 40).calculateCost(route10000km), PRECISION_EPSILON);
    }

    @Test
    public void calculateCost40Passenger() throws Exception {
        Route route500km = routeCreator.createValid500km();
        Route route10000km = routeCreator.createValid10000km();
        assertEquals(2.5, new Bus(Fuel.DIESEL, 25, 40, 40).calculateCost(route500km), PRECISION_EPSILON);
        assertEquals(50.0, new Bus(Fuel.DIESEL, 25, 40, 40).calculateCost(route10000km), PRECISION_EPSILON);
    }

    @Test
    public void constructorFullParamAllValid() throws Exception {
        Bus bus = new Bus(Fuel.DIESEL, 25, 40, 40);
        assertEquals(25, bus.getFuelConsumption(), PRECISION_EPSILON);
        assertEquals(40, bus.getPassengersCount());
        assertEquals(40, bus.getPassengersCapacity());
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamFuelConsumptionLowerZero() throws Exception {
        new Bus(Fuel.DIESEL, -15, 40, 40);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamFuelConsumptionEqualsZero() throws Exception {
        new Bus(Fuel.DIESEL, 0, 40, 40);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCountLowerZero() throws Exception {
        new Bus(Fuel.DIESEL, 25, -15, 40);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCountEqualsZero() throws Exception {
        new Bus(Fuel.DIESEL, 25, 0, 40);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCapacityLowerZero() throws Exception {
        new Bus(Fuel.DIESEL, 25, 40, -15);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCapacityEqualsZero() throws Exception {
        new Bus(Fuel.DIESEL, 25, 40, 0);
    }

    @Test
    public void constructorFullParamPassengersCountEqualsCapacity() throws Exception {
        Bus bus = new Bus(Fuel.DIESEL, 25, 40, 40);
        assertEquals(40, bus.getPassengersCount());
        assertEquals(40, bus.getPassengersCapacity());
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCountMoreThanCapacity() throws Exception {
        new Bus(Fuel.DIESEL, 25, 50, 40);
    }

    @Test(expected = WrongParameterException.class)
    public void setFuelConsumptionEqualsZero() throws Exception {
        defaultBus.setFuelConsumption(0);
    }

    @Test(expected = WrongParameterException.class)
    public void setFuelConsumptionLowerZero() throws Exception {
        defaultBus.setFuelConsumption(-11);
    }

    @Test
    public void setFuelConsumptionMoreZero() throws Exception {
        defaultBus.setFuelConsumption(11);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCountEqualsZero() throws Exception {
        defaultBus.setPassengersCount(0);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCountLowerZero() throws Exception {
        defaultBus.setPassengersCount(-11);
    }

    @Test
    public void setPassengersCountLowerCapacity() throws Exception {
        Bus bus = new Bus(Fuel.DIESEL, 25, 40, 40);
        bus.setPassengersCount(5);
        assertEquals(5, bus.getPassengersCount());
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCountMoreCapacity() throws Exception {
        new Bus(Fuel.DIESEL, 25, 40, 40).setPassengersCount(50);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCapacityEqualsZero() throws Exception {
        defaultBus.setPassengersCapacity(0);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCapacityLowerZero() throws Exception {
        defaultBus.setPassengersCapacity(-11);
    }

    @Test
    public void setPassengersCapacityMoreZeroMorePassengersCount() throws Exception {
        Bus bus = new Bus(Fuel.DIESEL, 25, 40, 40);
        bus.setPassengersCapacity(50);
        assertEquals(50, bus.getPassengersCapacity());
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCapacityMoreZeroLowerPassengersCount() throws Exception {
        new Bus(Fuel.DIESEL, 25, 40, 40).setPassengersCapacity(10);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setSpeedLowerZero() throws Exception {
        defaultBus.setSpeed(-15);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setSpeedEqualsZero() throws Exception {
        defaultBus.setSpeed(0);
    }

    @Test
    public void setSpeedMoreZero() throws Exception {
        defaultBus.setSpeed(14);
        assertEquals(14, defaultBus.getSpeed(), PRECISION_EPSILON);
    }
    @Test(expected = WrongParameterException.class)
    public void setSpeedNaN() throws Exception {
        defaultBus.setSpeed(Double.NaN);
    }

    @Test(expected = WrongParameterException.class)
    public void setSpeedNegInfinity() throws Exception {
        defaultBus.setSpeed(Double.NEGATIVE_INFINITY);
    }

    @Test(expected = WrongParameterException.class)
    public void setSpeedPosInfinity() throws Exception {
        defaultBus.setSpeed(Double.POSITIVE_INFINITY);
    }

    @Test (expected = WrongParameterException.class)
    public void setFuelConsumptionNaN() throws Exception{
        defaultBus.setFuelConsumption(Double.NaN);
    }

    @Test (expected = WrongParameterException.class)
    public void setFuelConsumptionPosInfinity() throws Exception{
        defaultBus.setFuelConsumption(Double.POSITIVE_INFINITY);
    }

    @Test (expected = WrongParameterException.class)
    public void setFuelConsumptionNegInfinity() throws Exception{
        defaultBus.setFuelConsumption(Double.NEGATIVE_INFINITY);
    }

}