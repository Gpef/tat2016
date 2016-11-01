package route.data;

import exceptions.WrongParameterException;
import org.testng.annotations.DataProvider;
import route.Checkpoint;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 01.11.2016
 */
public abstract class RouteDataProvider {

    @DataProvider(name = "invalidRoute")
    public static Object[][] getInvalidRouteData() throws WrongParameterException {
        return new Object[][]{
                {new ArrayList<>(Arrays.asList(new Checkpoint(1d, 1d), new Checkpoint(1d, 1d)))},
                {new ArrayList<>(Arrays.asList(new Checkpoint(1d, 1d), new Checkpoint(10d, 10d),
                        new Checkpoint(-1d, -1d), new Checkpoint(1d, 1d)))},
                {new ArrayList<Checkpoint>()}
        };
    }
}
