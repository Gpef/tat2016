package transport;

import exceptions.WrongParameterException;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import route.Route;
import transport.data.HumanDataProviders;
import transport.data.TransportParamsDataProviders;

import static org.testng.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 30.10.2016
 */
public class HumanTest {

    private Human defaultHuman;

    @BeforeMethod
    public void setUp() throws Exception {
        defaultHuman = new Human();
    }

    @Test(dataProvider = "validRouteTime", dataProviderClass = HumanDataProviders.class)
    public void calculateTime(Route route, double time) throws Exception {
        assertEquals(defaultHuman.calculateTime(route), time, 1e-3);
    }

    @Test(dataProvider = "validSpeed", dataProviderClass = TransportParamsDataProviders.class)
    public void setValidSpeed(double speed) throws Exception {
        defaultHuman.setSpeed(speed);
        AssertJUnit.assertEquals(speed, defaultHuman.getSpeed());
    }

    @Test(dataProvider = "invalidSpeed", dataProviderClass = TransportParamsDataProviders.class,
            expectedExceptions = WrongParameterException.class)
    public void setInvalidSpeed(double speed) throws Exception {
        defaultHuman.setSpeed(speed);
    }

}