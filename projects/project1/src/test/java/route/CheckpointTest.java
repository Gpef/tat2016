package route;

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
    public void constructorFields() throws Exception{
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
}