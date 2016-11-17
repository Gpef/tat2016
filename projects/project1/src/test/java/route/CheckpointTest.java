package route;

import exceptions.WrongParameterException;
import org.testng.annotations.Test;
import route.data.CheckpointDataProvider;

import static org.testng.Assert.*;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class CheckpointTest {

    @Test(dataProvider = "validConstructor", dataProviderClass = CheckpointDataProvider.class)
    public void validConstructorData(double pointX, double pointY) throws Exception {
        Checkpoint checkpoint = new Checkpoint(pointX, pointY);
        assertEquals(pointX, checkpoint.getX(), 1e-3);
        assertEquals(pointY, checkpoint.getY(), 1e-3);
    }

    @Test(dataProvider = "invalidConstructor", dataProviderClass = CheckpointDataProvider.class,
            expectedExceptions = WrongParameterException.class)
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

    @Test(dataProvider = "invalidPoint", dataProviderClass = CheckpointDataProvider.class,
            expectedExceptions = WrongParameterException.class)
    public void invalidSetXPoints(double pointX) throws Exception {
        Checkpoint checkpoint = new Checkpoint(0d, 0d);
        checkpoint.setX(pointX);
    }

    @Test(dataProvider = "invalidPoint", dataProviderClass = CheckpointDataProvider.class,
            expectedExceptions = WrongParameterException.class)
    public void invalidSetYPoints(double pointY) throws Exception {
        Checkpoint checkpoint = new Checkpoint(0d, 0d);
        checkpoint.setY(pointY);
    }
}