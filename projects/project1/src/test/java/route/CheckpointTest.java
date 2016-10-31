package route;

import exceptions.WrongParameterException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class CheckpointTest {

    @Test
    public void constructorFields() throws Exception {
        Checkpoint checkpoint = new Checkpoint(123.456789, 432.456789);
        assertEquals(123.456789, checkpoint.getX(), 1e-3);
        assertEquals(432.456789, checkpoint.getY(), 1e-3);
    }

    @Test
    public void equalsCheckpoints() throws Exception {
        Checkpoint p1 = new Checkpoint(11.33434, 314314.3413413);
        Checkpoint p2 = new Checkpoint(11.33434, 314314.3413413);
        Assert.assertTrue(p1.equals(p2));
    }

    @Test
    public void notEqualsCheckpoints() throws Exception {
        Checkpoint p1 = new Checkpoint(11.33434, 314314.3413413);
        Checkpoint p2 = new Checkpoint(11.334, 314314.342);
        assertFalse(p1.equals(p2));
    }

    @Test(expected = WrongParameterException.class)
    public void nanValuesCheckpoint1st() throws Exception {
        Checkpoint p1 = new Checkpoint(Double.NaN, 1);
    }

    @Test(expected = WrongParameterException.class)
    public void nanValuesCheckpoint2nd() throws Exception {
        Checkpoint p1 = new Checkpoint(1, Double.NaN);
    }

    @Test(expected = WrongParameterException.class)
    public void negativeInfinityValuesCheckpoint1st() throws Exception {
        Checkpoint p1 = new Checkpoint(Double.NEGATIVE_INFINITY, 1);
    }

    @Test(expected = WrongParameterException.class)
    public void negativeInfinityValuesCheckpoint2nd() throws Exception {
        Checkpoint p1 = new Checkpoint(1, Double.NEGATIVE_INFINITY);
    }

    @Test(expected = WrongParameterException.class)
    public void positiveInfinityValuesCheckpoint1st() throws Exception {
        Checkpoint p1 = new Checkpoint(Double.POSITIVE_INFINITY, 1);
    }

    @Test(expected = WrongParameterException.class)
    public void positiveInfinityValuesCheckpoint2nd() throws Exception {
        Checkpoint p1 = new Checkpoint(1, Double.POSITIVE_INFINITY);
    }

    @Test(expected = WrongParameterException.class)
    public void setXNaN() throws Exception{
        Checkpoint checkpoint = new Checkpoint(1,1);
        checkpoint.setX(Double.NaN);
    }

    @Test(expected = WrongParameterException.class)
    public void setYNaN() throws Exception{
        Checkpoint checkpoint = new Checkpoint(1,1);
        checkpoint.setY(Double.NaN);
    }

    @Test(expected = WrongParameterException.class)
    public void setXPosInfinity() throws Exception{
        Checkpoint checkpoint = new Checkpoint(1,1);
        checkpoint.setX(Double.POSITIVE_INFINITY);
    }

    @Test(expected = WrongParameterException.class)
    public void setYPosInfinity() throws Exception{
        Checkpoint checkpoint = new Checkpoint(1,1);
        checkpoint.setY(Double.POSITIVE_INFINITY);
    }

    @Test(expected = WrongParameterException.class)
    public void setXNegInfinity() throws Exception{
        Checkpoint checkpoint = new Checkpoint(1,1);
        checkpoint.setX(Double.NEGATIVE_INFINITY);
    }

    @Test(expected = WrongParameterException.class)
    public void setYNegInfinity() throws Exception{
        Checkpoint checkpoint = new Checkpoint(1,1);
        checkpoint.setY(Double.NEGATIVE_INFINITY);
    }
}