package transport.data;

import org.testng.annotations.DataProvider;
import transport.RouteCreator;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 01.11.2016
 */
public abstract class BicycleDataProviders {

    @DataProvider(name = "validRouteTime")
    public static Object[][] getValidRouteTimeData() throws Exception {
        RouteCreator routeCreator = new RouteCreator();
        return new Object[][]{
                {routeCreator.createValid500km(), 31.25},
                {routeCreator.createValid10000km(), 625.0},
        };
    }

}
