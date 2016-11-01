package route;

import exceptions.RouteException;
import exceptions.WrongParameterException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class RouteTest {

    @DataProvider(name = "invalidRoute")
    public Object[][] getInvalidRouteData() throws WrongParameterException {
        return new Object[][]{
                {new ArrayList<>(Arrays.asList(new Checkpoint(1d, 1d), new Checkpoint(1d, 1d)))},
                {new ArrayList<>(Arrays.asList(new Checkpoint(1d, 1d), new Checkpoint(10d, 10d),
                        new Checkpoint(-1d, -1d), new Checkpoint(1d, 1d)))},
                {new ArrayList<Checkpoint>()}
        };
    }

    @Test(dataProvider = "invalidRoute", expectedExceptions = RouteException.class)
    public void invalidRoute(ArrayList<Checkpoint> checkpoints) throws Exception {
        new Route(checkpoints);
    }
}
