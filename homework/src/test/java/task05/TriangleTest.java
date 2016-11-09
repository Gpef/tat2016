package task05;

import org.testng.annotations.Test;
import task05.data.TriangleDataProviders;
import task05.exceptions.WrongSideLengthException;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.11.2016
 */
public class TriangleTest {

    private Triangle triangle;

    @Test(dataProvider = "invalidTriangles", dataProviderClass = TriangleDataProviders.class,
            expectedExceptions = WrongSideLengthException.class)
    public void invalidConstructorData(BigDecimal sideA, BigDecimal sideB, BigDecimal sideC) throws Exception {
        triangle = new Triangle(sideA, sideB, sideC);
    }

    @Test(dataProvider = "validIsoscelesTriangle", dataProviderClass = TriangleDataProviders.class)
    public void validIsoscelesConstructorData(BigDecimal sideA, BigDecimal sideB, BigDecimal sideC) throws Exception {
        triangle = new Triangle(sideA, sideB, sideC);
        assertEquals(triangle.getType(), Triangle.ISOSCELES);
    }

    @Test(dataProvider = "validEquilateralTriangle", dataProviderClass = TriangleDataProviders.class)
    public void validEquilateralConstructorData(BigDecimal sideA, BigDecimal sideB, BigDecimal sideC) throws Exception {
        triangle = new Triangle(sideA, sideB, sideC);
        assertEquals(triangle.getType(), Triangle.EQUILATERAL);
    }

    @Test(dataProvider = "validCommonTriangle", dataProviderClass = TriangleDataProviders.class)
    public void validCommonConstructorData(BigDecimal sideA, BigDecimal sideB, BigDecimal sideC) throws Exception {
        triangle = new Triangle(sideA, sideB, sideC);
        assertEquals(triangle.getType(), Triangle.COMMON);
    }
}