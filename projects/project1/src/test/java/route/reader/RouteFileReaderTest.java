package route.reader;

import org.junit.Test;

import java.io.File;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 30.10.2016
 */
public class RouteFileReaderTest {

    @Test(expected = exceptions.WrongParameterException.class)
    public void createReaderWithNoFile() throws Exception {
        new RouteFileReader(new File("thisIsNotA.File"));
    }

}