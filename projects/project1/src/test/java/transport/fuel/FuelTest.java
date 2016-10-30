package transport.fuel;

import exceptions.WrongParameterException;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 30.10.2016
 */
public class FuelTest {

    Fuel fuel;

    @Before
    public void setUp(){
        fuel = Fuel.PETROL;
    }

    @Test(expected = WrongParameterException.class)
    public void setPriceLowerZero() throws Exception {
        fuel.setPrice(-11);
    }

    @Test(expected = WrongParameterException.class)
    public void setPriceEqualsZero() throws Exception {
        fuel.setPrice(0);
    }
}