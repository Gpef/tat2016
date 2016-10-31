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
public class CarTest {
    private static double PRECISION_EPSILON = 1e-3;
    private Car defaultCar;
    private RouteCreator routeCreator;

    @Before
    public void setUp() throws Exception {
        defaultCar = new Car(Fuel.PETROL, 5, 1, 5);
        routeCreator = new RouteCreator();
    }

    @Test
    public void calculateTime() throws Exception {
        Route route500km = routeCreator.createValid500km();
        Route route10000km = routeCreator.createValid10000km();
        assertEquals(5.5555, new Car(Fuel.PETROL, 5, 1, 5).calculateTime(route500km), PRECISION_EPSILON);
        assertEquals(111.1111, new Car(Fuel.PETROL, 5, 1, 5).calculateTime(route10000km), PRECISION_EPSILON);
    }

    @Test
    public void calculateCost1Passenger() throws Exception {
        Route route500km = routeCreator.createValid500km();
        Route route10000km = routeCreator.createValid10000km();
        assertEquals(25.0, new Car(Fuel.PETROL, 5, 1, 5).calculateCost(route500km), PRECISION_EPSILON);
        assertEquals(500.0, new Car(Fuel.PETROL, 5, 1, 5).calculateCost(route10000km), PRECISION_EPSILON);
    }

    @Test
    public void calculateCost5Passenger() throws Exception {
        Route route500km = routeCreator.createValid500km();
        Route route10000km = routeCreator.createValid10000km();
        assertEquals(5.0, new Car(Fuel.PETROL, 5, 5, 5).calculateCost(route500km), PRECISION_EPSILON);
        assertEquals(100.0, new Car(Fuel.PETROL, 5, 5, 5).calculateCost(route10000km), PRECISION_EPSILON);
    }

    @Test
    public void constructorFulParamAllValid() throws Exception {
        Car bus = new Car(Fuel.PETROL, 5, 1, 5);
        assertEquals(5, bus.getFuelConsumption(), PRECISION_EPSILON);
        assertEquals(1, bus.getPassengersCount());
        assertEquals(5, bus.getPassengersCapacity());
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamFuelConsumptionLowerZero() throws Exception {
        new Car(Fuel.PETROL, -15, 1, 5);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamFuelConsumptionEqualsZero() throws Exception {
        new Car(Fuel.PETROL, 0, 1, 5);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCountLowerZero() throws Exception {
        new Car(Fuel.PETROL, 5, -15, 5);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCountEqualsZero() throws Exception {
        new Car(Fuel.PETROL, 5, 0, 5);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCapacityLowerZero() throws Exception {
        new Car(Fuel.PETROL, 5, 1, -15);
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCapacityEqualsZero() throws Exception {
        new Car(Fuel.PETROL, 5, 1, 0);
    }

    @Test
    public void constructorFullParamPassengersCountEqualsCapacity() throws Exception {
        Car car = new Car(Fuel.PETROL, 5, 1, 1);
        assertEquals(1, car.getPassengersCount());
        assertEquals(1, car.getPassengersCapacity());
    }

    @Test(expected = WrongParameterException.class)
    public void constructorFullParamPassengersCountMoreThanCapacity() throws Exception {
        new Car(Fuel.PETROL, 5, 10, 5);
    }

    @Test(expected = WrongParameterException.class)
    public void setFuelConsumptionEqualsZero() throws Exception {
        defaultCar.setFuelConsumption(0);
    }

    @Test(expected = WrongParameterException.class)
    public void setFuelConsumptionLowerZero() throws Exception {
        defaultCar.setFuelConsumption(-11);
    }

    @Test
    public void setFuelConsumptionMoreZero() throws Exception {
        defaultCar.setFuelConsumption(11);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCountEqualsZero() throws Exception {
        defaultCar.setPassengersCount(0);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCountLowerZero() throws Exception {
        defaultCar.setPassengersCount(-11);
    }

    @Test
    public void setPassengersCountLowerCapacity() throws Exception {
        Car car = new Car(Fuel.PETROL, 5, 1, 5);
        car.setPassengersCount(3);
        assertEquals(3, car.getPassengersCount());
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCountMoreCapacity() throws Exception {
        new Car(Fuel.PETROL, 5, 1, 5).setPassengersCount(10);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCapacityEqualsZero() throws Exception {
        defaultCar.setPassengersCapacity(0);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCapacityLowerZero() throws Exception {
        defaultCar.setPassengersCapacity(-11);
    }

    @Test
    public void setPassengersCapacityMoreZeroMorePassengersCount() throws Exception {
        Car car = new Car(Fuel.PETROL, 5, 1, 5);
        car.setPassengersCapacity(6);
        assertEquals(6, car.getPassengersCapacity());
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setPassengersCapacityMoreZeroLowerPassengersCount() throws Exception {
        new Car(Fuel.PETROL, 5, 5, 5).setPassengersCapacity(2);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setSpeedLowerZero() throws Exception {
        defaultCar.setSpeed(-15);
    }

    @Test(expected = exceptions.WrongParameterException.class)
    public void setSpeedEqualsZero() throws Exception {
        defaultCar.setSpeed(0);
    }

    @Test
    public void setSpeedMoreZero() throws Exception {
        defaultCar.setSpeed(14);
        assertEquals(14, defaultCar.getSpeed(), PRECISION_EPSILON);
    }

    @Test(expected = WrongParameterException.class)
    public void setSpeedNaN() throws Exception {
        defaultCar.setSpeed(Double.NaN);
    }

    @Test(expected = WrongParameterException.class)
    public void setSpeedNegInfinity() throws Exception {
        defaultCar.setSpeed(Double.NEGATIVE_INFINITY);
    }

    @Test(expected = WrongParameterException.class)
    public void setSpeedPosInfinity() throws Exception {
        defaultCar.setSpeed(Double.POSITIVE_INFINITY);
    }

    @Test (expected = WrongParameterException.class)
    public void setFuelConsumptionNaN() throws Exception{
        defaultCar.setFuelConsumption(Double.NaN);
    }

    @Test (expected = WrongParameterException.class)
    public void setFuelConsumptionPosInfinity() throws Exception{
        defaultCar.setFuelConsumption(Double.POSITIVE_INFINITY);
    }

    @Test (expected = WrongParameterException.class)
    public void setFuelConsumptionNegInfinity() throws Exception{
        defaultCar.setFuelConsumption(Double.NEGATIVE_INFINITY);
    }
}