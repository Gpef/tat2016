package task05;

import org.testng.annotations.Test;
import task05.data.TriangleDataProviders;
import task05.exceptions.TriangleException;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.11.2016
 */
public class TriangleTest {
    private Triangle triangle;

    @Test(dataProvider = "validXMLTriangle", dataProviderClass = TriangleDataProviders.class)
    public void validTriangleConstructorData(
            BigDecimal sideA,
            BigDecimal sideB,
            BigDecimal sideC,
            short triangleType
    ) throws Exception {
        triangle = new Triangle(sideA, sideB, sideC);
        assertEquals(triangle.getType(), triangleType);
    }

    @Test(dataProvider = "invalidXMLTriangle", dataProviderClass = TriangleDataProviders.class,
            expectedExceptions = TriangleException.class)
    public void invalidTriangleConstructorData(
            BigDecimal sideA,
            BigDecimal sideB,
            BigDecimal sideC
    ) throws Exception {
        triangle = new Triangle(sideA, sideB, sideC);
    }
}