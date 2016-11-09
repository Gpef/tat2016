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
        return new Object[][]{
                {BigDecimal.valueOf(3), BigDecimal.valueOf(4), BigDecimal.valueOf(5)},
        };
    }

    @DataProvider(name = "validEquilateralTriangle")
    public static Object[][] getValidEquilateralTriangleData() {
        return new Object[][]{
                {BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(1)},
        };
    }

    @DataProvider(name = "validIsoscelesTriangle")
    public static Object[][] getValidIsoscelesTriangleData() {
        return new Object[][]{
                {BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(9)},
                {BigDecimal.valueOf(10), BigDecimal.valueOf(9), BigDecimal.valueOf(10)},
                {BigDecimal.valueOf(9), BigDecimal.valueOf(10), BigDecimal.valueOf(10)},
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
