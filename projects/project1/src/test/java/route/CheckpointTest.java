package route;

import exceptions.WrongParameterException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class CheckpointTest {

    private Checkpoint checkpoint;

    @DataProvider(name = "invalidConstructor")
    public Object[][] getInvalidConstructorData() throws WrongParameterException {
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
    public Object[][] getValidConstructorData() throws WrongParameterException {
        return new Object[][]{
                {0d, 0d},
                {123.456789, 432.456789},
                {-123.456789, -432.456789}
        };
    }

    @DataProvider(name = "invalidPoint")
    public Object[][] getInvalidPointData() throws WrongParameterException {
        return new Object[][]{
                {Double.NaN},
                {Double.NEGATIVE_INFINITY},
                {Double.POSITIVE_INFINITY}
        };
    }

    @Test(dataProvider = "validConstructor")
    public void validConstructorData(double pointX, double pointY) throws Exception {
        Checkpoint checkpoint = new Checkpoint(pointX, pointY);
        assertEquals(pointX, checkpoint.getX(), 1e-3);
        assertEquals(pointY, checkpoint.getY(), 1e-3);
    }

    @Test(dataProvider = "invalidConstructor", expectedExceptions = WrongParameterException.class)
    public void invalidConstructorData(double pointX, double pointY) throws Exception {
        new Checkpoint(pointX, pointY);
    }

    @Test
    public void equalsCheckpoints() throws Exception {
        Checkpoint p1 = new Checkpoint(11.33434, 314314.3413413);
        Checkpoint p2 = new Checkpoint(11.33434, 314314.3413413);
        assertTrue(p1.equals(p2));
    }

    @Test
    public void notEqualsCheckpoints() throws Exception {
        Checkpoint p1 = new Checkpoint(11.33434, 314314.3413413);
        Checkpoint p2 = new Checkpoint(11.33434, 314314.3412);
        assertFalse(p1.equals(p2));
    }

    @Test(dataProvider = "invalidPoint", expectedExceptions = WrongParameterException.class)
    public void invalidSetXPoints(double pointX) throws Exception {
        Checkpoint checkpoint = new Checkpoint(0d, 0d);
        checkpoint.setX(pointX);
    }

    @Test(dataProvider = "invalidPoint", expectedExceptions = WrongParameterException.class)
    public void invalidSetYPoints(double pointY) throws Exception {
        Checkpoint checkpoint = new Checkpoint(0d, 0d);
        checkpoint.setY(pointY);
    }
}