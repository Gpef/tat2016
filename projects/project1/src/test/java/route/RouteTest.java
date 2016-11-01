package route;

import exceptions.RouteException;
import org.testng.annotations.Test;
import route.data.RouteDataProvider;

import java.util.ArrayList;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.10.2016
 */
public class RouteTest {

    @Test(dataProvider = "invalidRoute", dataProviderClass = RouteDataProvider.class,
            expectedExceptions = RouteException.class)
    public void invalidRoute(ArrayList<Checkpoint> checkpoints) throws Exception {
        new Route(checkpoints);
    }
}
