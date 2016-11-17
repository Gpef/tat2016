package transport.data;

import org.testng.annotations.DataProvider;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 01.11.2016
 */
public abstract class TransportParamsDataProviders {

    @DataProvider(name = "validSpeed")
    public static Object[][] getValidSpeedData() throws Exception {
        return new Object[][]{
                {1d},
                {Double.MIN_VALUE},
                {Double.MAX_VALUE}
        };
    }

    @DataProvider(name = "invalidSpeed")
    public static Object[][] getInvalidSpeedData() throws Exception {
        return new Object[][]{
                {Double.NaN},
                {Double.POSITIVE_INFINITY},
                {Double.NEGATIVE_INFINITY},
                {0d},
                {-10d},
        };
    }

    @DataProvider(name = "validFuelConsumption")
    public static Object[][] getValidFuelConsumptionData() throws Exception {
        return new Object[][]{
                {Double.MAX_VALUE},
                {Double.MIN_VALUE},
                {1e-3d}
        };
    }

    @DataProvider(name = "invalidFuelConsumption")
    public static Object[][] getInvalidFuelConsumptionData() throws Exception {
        return new Object[][]{
                {Double.NaN},
                {Double.POSITIVE_INFINITY},
                {Double.NEGATIVE_INFINITY},
                {0d},
                {-100d},
                {-1e-300d}
        };
    }

    @DataProvider(name = "invalidPassengers")
    public static Object[][] getInvalidPassengersData() throws Exception {
        return new Object[][]{
                {-100},
                {0},
                {Integer.MIN_VALUE}
        };
    }
}
