package transport.fuel;

import exceptions.WrongParameterException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 30.10.2016
 */
public class FuelTest {

    Fuel fuel;

    @BeforeMethod
    public void setUp(){
        fuel = Fuel.FOR_TESTS;
    }

    @Test(expectedExceptions = WrongParameterException.class)
    public void setPriceLowerZero() throws Exception {
        fuel.setPrice(-11);
    }

    @Test(expectedExceptions = WrongParameterException.class)
    public void setPriceEqualsZero() throws Exception {
        fuel.setPrice(0);
    }

    @Test
    public void setPriceMoreZero() throws Exception{
        fuel.setPrice(111);
        assertEquals(111, fuel.getPrice(), 1e-3);
    }
}