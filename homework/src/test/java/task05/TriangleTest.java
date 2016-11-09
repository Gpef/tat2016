package task05;

import org.testng.annotations.Test;
import task05.data.TriangleDataProviders;
import task05.exceptions.TriangleNotExistsException;
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

    @Test(dataProvider = "invalidTriangleXML", dataProviderClass = TriangleDataProviders.class,
            expectedExceptions = WrongSideLengthException.class)
    public void invalidConstructorData(BigDecimal sideA, BigDecimal sideB, BigDecimal sideC) throws Exception {
        triangle = new Triangle(sideA, sideB, sideC);
    }

    @Test(dataProvider = "validIsoscelesTriangleXML", dataProviderClass = TriangleDataProviders.class)
    public void validIsoscelesConstructorData(BigDecimal sideA, BigDecimal sideB, BigDecimal sideC) throws Exception {
        triangle = new Triangle(sideA, sideB, sideC);
        assertEquals(triangle.getType(), Triangle.ISOSCELES);
    }

    @Test(dataProvider = "validEquilateralTriangleXML", dataProviderClass = TriangleDataProviders.class)
    public void validEquilateralConstructorData(BigDecimal sideA, BigDecimal sideB, BigDecimal sideC) throws Exception {
        triangle = new Triangle(sideA, sideB, sideC);
        assertEquals(triangle.getType(), Triangle.EQUILATERAL);
    }

    @Test(dataProvider = "validCommonTriangleXML", dataProviderClass = TriangleDataProviders.class)
    public void validCommonConstructorData(BigDecimal sideA, BigDecimal sideB, BigDecimal sideC) throws Exception {
        triangle = new Triangle(sideA, sideB, sideC);
        assertEquals(triangle.getType(), Triangle.COMMON);
    }

    @Test(dataProvider = "validNotExistTriangleXML", dataProviderClass = TriangleDataProviders.class,
            expectedExceptions = TriangleNotExistsException.class)
    public void validNotExistsConstructorData(BigDecimal sideA, BigDecimal sideB, BigDecimal sideC) throws Exception {
        triangle = new Triangle(sideA, sideB, sideC);
    }
}