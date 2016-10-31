package transport;

import exceptions.WrongParameterException;
import org.junit.Before;
import org.junit.Test;
import route.Route;

import static org.junit.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 30.10.2016
 */
public class TardisTest {

    private static double PRECISION_EPSILON = 1e-3;
    private static double DEFAULT_MOVING_TIME = 5.5e-4;
    private Tardis defaultTardis;
    private RouteCreator routeCreator;

    @Before
    public void setUp() throws Exception {
        defaultTardis = new Tardis(10, 1, 1);
        routeCreator = new RouteCreator();
    }

    @Test
    public void calculateTime() throws Exception {
        Route route500km = routeCreator.createValid500km();
        Route route10000km = routeCreator.createValid10000km();
        assertEquals(DEFAULT_MOVING_TIME, new Tardis(10, 1, 1).calculateTime(route500km), PRECISION_EPSILON);
        assertEquals(DEFAULT_MOVING_TIME, new Tardis(10, 1, 1).calculateTime(route10000km), PRECISION_EPSILON);
    }

    @Test
    public void calculateCost1Passenger() throws Exception {
        Route route500km = routeCreator.createValid500km();
        Route route10000km = routeCreator.createValid10000km();
        assertEquals(50, new Tardis(10, 1, 1).calculateCost(route500km), PRECISION_EPSILON);
        assertEquals(1000.0, new Tardis(10, 1, 1).calculateCost(route10000km), PRECISION_EPSILON);
    }

    @Test
    public void calculateCost10Passenger() throws Exception {
        Route route500km = routeCreator.createValid500km();
        Route route10000km = routeCreator.createValid10000km();
        assertEquals(5, new Tardis(10, 10, 10).calculateCost(route500km), PRECISION_EPSILON);
        assertEquals(100.0, new Tardis(10, 10, 10).calculateCost(route10000km), PRECISION_EPSILON);
    }

    @Test
    public void constructorFulParamAllValid() throws Exception {
        Tardis bus = new Tardis(10, 1, 1);
        assertEquals(10, bus.getFuelConsumption(), PRECISION_EPSILON);
        assertEquals(1, bus.getPassengersCount());
        assertEquals(1, bus.getPassengersCapacity());
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamFuelConsumptionLowerZero() throws Exception {
        new Tardis(-15, 1, 1);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamFuelConsumptionEqualsZero() throws Exception {
        new Tardis(0, 1, 1);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCountLowerZero() throws Exception {
        new Tardis(10, -1, 1);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCountEqualsZero() throws Exception {
        new Tardis(10, 0, 1);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCapacityLowerZero() throws Exception {
        new Tardis(10, 1, -1);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCapacityEqualsZero() throws Exception {
        new Tardis(10, 1, 0);
    }

    @Test
    public void constructorFullParamPassengersCountEqualsCapacity() throws Exception {
        Tardis tardis = new Tardis(10, 1, 1);
        assertEquals(1, tardis.getPassengersCount());
        assertEquals(1, tardis.getPassengersCapacity());
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCountMoreThanCapacity() throws Exception {
        new Tardis(10, 5, 1);
    }

    @Test(expected = WrongParameterException.class)
    public void setFuelConsumptionEqualsZero() throws Exception {
        defaultTardis.setFuelConsumption(0);
    }

    @Test(expected = WrongParameterException.class)
    public void setFuelConsumptionLowerZero() throws Exception {
        defaultTardis.setFuelConsumption(-11);
    }

    @Test
    public void setFuelConsumptionMoreZero() throws Exception {
        defaultTardis.setFuelConsumption(11);
        assertEquals(11, defaultTardis.getFuelConsumption(), PRECISION_EPSILON);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCountEqualsZero() throws Exception {
        defaultTardis.setPassengersCount(0);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCountLowerZero() throws Exception {
        defaultTardis.setPassengersCount(-11);
    }

    @Test
    public void setPassengersCountLowerCapacity() throws Exception {
        Tardis tardis = new Tardis(10, 1, 5);
        tardis.setPassengersCount(2);
        assertEquals(2, tardis.getPassengersCount());
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCountMoreCapacity() throws Exception {
        new Tardis(10, 1, 1).setPassengersCount(5);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCapacityEqualsZero() throws Exception {
        defaultTardis.setPassengersCapacity(0);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCapacityLowerZero() throws Exception {
        defaultTardis.setPassengersCapacity(-11);
    }

    @Test
    public void setPassengersCapacityMoreZeroMorePassengersCount() throws Exception {
        Tardis tardis = new Tardis(10, 1, 1);
        tardis.setPassengersCapacity(10);
        assertEquals(10, tardis.getPassengersCapacity());
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCapacityMoreZeroLowerPassengersCount() throws Exception {
        new Tardis(10, 5, 5).setPassengersCapacity(1);
    }

    @Test (expected = WrongParameterException.class)
    public void setFuelConsumptionNaN() throws Exception{
        defaultTardis.setFuelConsumption(Double.NaN);
    }

    @Test (expected = WrongParameterException.class)
    public void setFuelConsumptionPosInfinity() throws Exception{
        defaultTardis.setFuelConsumption(Double.POSITIVE_INFINITY);
    }

    @Test (expected = WrongParameterException.class)
    public void setFuelConsumptionNegInfinity() throws Exception{
        defaultTardis.setFuelConsumption(Double.NEGATIVE_INFINITY);
    }
}