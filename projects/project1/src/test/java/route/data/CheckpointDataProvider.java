package route.data;

import exceptions.WrongParameterException;
import org.testng.annotations.DataProvider;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 01.11.2016
 */
public abstract class CheckpointDataProvider {

    @DataProvider(name = "invalidConstructor")
    public static Object[][] getInvalidConstructorData() throws WrongParameterException {
        return new Object[][]{
                {0d, Double.NaN},
                {0d, Double.POSITIVE_INFINITY},
                {0d, Double.NEGATIVE_INFINITY},
                {Double.NaN, 0d},
                {Double.POSITIVE_INFINITY, 0d},
                {Double.NEGATIVE_INFINITY, 0d},
        };
    }

    @DataProvider(name = "validConstructor")
    public static Object[][] getValidConstructorData() throws WrongParameterException {
        return new Object[][]{
                {0d, 0d},
                {123.456789, 432.456789},
                {-123.456789, -432.456789}
        };
    }

    @DataProvider(name = "invalidPoint")
    public static Object[][] getInvalidPointData() throws WrongParameterException {
        return new Object[][]{
                {Double.NaN},
                {Double.NEGATIVE_INFINITY},
                {Double.POSITIVE_INFINITY}
        };
    }
}
