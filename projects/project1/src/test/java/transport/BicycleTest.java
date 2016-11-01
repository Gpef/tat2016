package transport;

import exceptions.WrongParameterException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import route.Route;
import transport.data.BicycleDataProviders;
import transport.data.TransportParamsDataProviders;

import static org.testng.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class BicycleTest {

    private Bicycle defaultBicycle;

    @BeforeMethod
    public void setUp() throws Exception {
        defaultBicycle = new Bicycle();
    }

    @Test(dataProvider = "validRouteTime", dataProviderClass = BicycleDataProviders.class)
    public void calculateTime(Route route, double time) throws Exception {
        assertEquals(defaultBicycle.calculateTime(route), time, 1e-3);
    }

    @Test(dataProvider = "invalidSpeed", dataProviderClass = TransportParamsDataProviders.class,
            expectedExceptions = WrongParameterException.class)
    public void setInvalidSpeed(double speed) throws Exception {
        defaultBicycle.setSpeed(speed);
    }

    @Test(dataProvider = "validSpeed", dataProviderClass = TransportParamsDataProviders.class)
    public void setValidSpeed(double speed) throws Exception {
        defaultBicycle.setSpeed(speed);
        assertEquals(defaultBicycle.getSpeed(), speed);
    }

}