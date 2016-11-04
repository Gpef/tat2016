package task05.data;

import org.testng.annotations.DataProvider;

import java.math.BigDecimal;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.11.2016
 */
public class TriangleDataProviders {

    @DataProvider(name = "invalidTriangles")
    public static Object[][] getInvalidTriangleData() {
        return new Object[][]{
                {BigDecimal.valueOf(0), BigDecimal.valueOf(1), BigDecimal.valueOf(1)},
                {BigDecimal.valueOf(1), BigDecimal.valueOf(0), BigDecimal.valueOf(1)},
                {BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(0)},

                {BigDecimal.valueOf(-1), BigDecimal.valueOf(1), BigDecimal.valueOf(1)},
                {BigDecimal.valueOf(1), BigDecimal.valueOf(-1), BigDecimal.valueOf(1)},
                {BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(-1)},

                {null, BigDecimal.valueOf(1), BigDecimal.valueOf(1)},
                {BigDecimal.valueOf(1), null, BigDecimal.valueOf(1)},
                {BigDecimal.valueOf(1), BigDecimal.valueOf(1), null}
        };
    }

    @DataProvider(name = "validCommonTriangle")
    public static Object[][] getValidCommonTriangleData() {
        BigDecimal maxDouble = BigDecimal.valueOf(Double.MAX_VALUE);
        return new Object[][]{
                {BigDecimal.valueOf(3), BigDecimal.valueOf(4), BigDecimal.valueOf(5)},

                {maxDouble.multiply(maxDouble).subtract(BigDecimal.valueOf(100)),
                        maxDouble.multiply(maxDouble), BigDecimal.valueOf(1000)},

                {BigDecimal.valueOf(1000), maxDouble.multiply(maxDouble).subtract(BigDecimal.valueOf(100)),
                        maxDouble.multiply(maxDouble)},

                {maxDouble.multiply(maxDouble), BigDecimal.valueOf(1000),
                        maxDouble.multiply(maxDouble).subtract(BigDecimal.valueOf(100))}
        };
    }

    @DataProvider(name = "validEquilateralTriangle")
    public static Object[][] getValidEquilateralTriangleData() {
        BigDecimal maxDouble = BigDecimal.valueOf(Double.MAX_VALUE);
        BigDecimal minDouble = BigDecimal.valueOf(Double.MIN_VALUE);
        return new Object[][]{
                {BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(1)},
                {maxDouble, maxDouble, maxDouble},
                {minDouble, minDouble, minDouble}
        };
    }

    @DataProvider(name = "validIsoscelesTriangle")
    public static Object[][] getValidIsoscelesTriangleData() {
        BigDecimal maxDouble = BigDecimal.valueOf(Double.MAX_VALUE);
        BigDecimal minDouble = BigDecimal.valueOf(Double.MIN_VALUE);
        return new Object[][]{
                {BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(9)},
                {BigDecimal.valueOf(10), BigDecimal.valueOf(9), BigDecimal.valueOf(10)},
                {BigDecimal.valueOf(9), BigDecimal.valueOf(10), BigDecimal.valueOf(10)},

                {maxDouble, maxDouble, BigDecimal.valueOf(10)},
                {maxDouble, BigDecimal.valueOf(10), maxDouble},
                {BigDecimal.valueOf(10), maxDouble, maxDouble},

                {minDouble, BigDecimal.valueOf(10), BigDecimal.valueOf(10)},
                {BigDecimal.valueOf(10), minDouble, BigDecimal.valueOf(10)},
                {BigDecimal.valueOf(10), BigDecimal.valueOf(10), minDouble},
        };
    }

    @DataProvider(name = "validNotExistTriangle")
    public static Object[][] getValidNotExistTriangleData() {
        return new Object[][]{
                {BigDecimal.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(100)},
                {BigDecimal.valueOf(10), BigDecimal.valueOf(100), BigDecimal.valueOf(1)},
                {BigDecimal.valueOf(100), BigDecimal.valueOf(10), BigDecimal.valueOf(1)},

                {BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(2)},
                {BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(1)},
                {BigDecimal.valueOf(2), BigDecimal.valueOf(1), BigDecimal.valueOf(1)}

        };
    }

}
