package task05.data;

import org.testng.annotations.DataProvider;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.11.2016
 */
public class TriangleDataProviders {

    private static final String DATA_FILE_PATH = "test\\data\\triangleTest.xml";

    private static final String SIDE_A = "side_a";
    private static final String SIDE_B = "side_b";
    private static final String SIDE_C = "side_c";

    private static final String INVALID_TRIANGLE = "invalid";
    private static final String COMMON_TRIANGLE = "validCommon";
    private static final String EQUILATERAL_TRIANGLE = "validEquilateral";
    private static final String ISOSCELES_TRIANGLE = "validIsosceles";
    private static final String NOT_EXISTS_TRIANGLE = "validNotExists";

    // HardCode DataProviders

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

    // XML DataProviders

    @DataProvider(name = "invalidTriangleXML")
    public static Object[][] getInvalidTrianglesXMLData() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        File testDataFile = new File(DATA_FILE_PATH);
        Document document = documentBuilder.parse(testDataFile);
        NodeList nodes = document.getElementsByTagName(INVALID_TRIANGLE);
        Object[][] result = new BigDecimal[nodes.getLength()][];
        for (int i = 0; i < nodes.getLength(); i++) {
            NamedNodeMap attr = nodes.item(i).getAttributes();
            result[i] = new BigDecimal[]{
                    attr.getNamedItem(SIDE_A) == null ? null : new BigDecimal(attr.getNamedItem(SIDE_A).getNodeValue()),
                    attr.getNamedItem(SIDE_B) == null ? null : new BigDecimal(attr.getNamedItem(SIDE_B).getNodeValue()),
                    attr.getNamedItem(SIDE_C) == null ? null : new BigDecimal(attr.getNamedItem(SIDE_C).getNodeValue())
            };
        }

        return result;
    }

    @DataProvider(name = "validCommonTriangleXML")
    public static Object[][] getValidCommonTriangleXMLData() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(new File(DATA_FILE_PATH));
        NodeList nodes = document.getElementsByTagName(COMMON_TRIANGLE);
        Object[][] result = new BigDecimal[nodes.getLength()][];
        for (int i = 0; i < nodes.getLength(); i++) {
            NamedNodeMap attributes = nodes.item(i).getAttributes();
            result[i] = new BigDecimal[]{
                    new BigDecimal(attributes.getNamedItem(SIDE_A).getNodeValue()),
                    new BigDecimal(attributes.getNamedItem(SIDE_B).getNodeValue()),
                    new BigDecimal(attributes.getNamedItem(SIDE_C).getNodeValue())
            };
        }

        return result;
    }

    @DataProvider(name = "validEquilateralTriangleXML")
    public static Object[][] getValidEquilateralTriangleXMLData() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(new File(DATA_FILE_PATH));
        NodeList nodes = document.getElementsByTagName(EQUILATERAL_TRIANGLE);
        Object[][] result = new BigDecimal[nodes.getLength()][];
        for (int i = 0; i < nodes.getLength(); i++) {
            NamedNodeMap attributes = nodes.item(i).getAttributes();
            result[i] = new BigDecimal[]{
                    new BigDecimal(attributes.getNamedItem(SIDE_A).getNodeValue()),
                    new BigDecimal(attributes.getNamedItem(SIDE_B).getNodeValue()),
                    new BigDecimal(attributes.getNamedItem(SIDE_C).getNodeValue())
            };
        }

        return result;
    }

    @DataProvider(name = "validIsoscelesTriangleXML")
    public static Object[][] getValidIsoscelesTriangleXMLData() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(new File(DATA_FILE_PATH));
        NodeList nodes = document.getElementsByTagName(ISOSCELES_TRIANGLE);
        Object[][] result = new BigDecimal[nodes.getLength()][];
        for (int i = 0; i < nodes.getLength(); i++) {
            NamedNodeMap attributes = nodes.item(i).getAttributes();
            result[i] = new BigDecimal[]{
                    new BigDecimal(attributes.getNamedItem(SIDE_A).getNodeValue()),
                    new BigDecimal(attributes.getNamedItem(SIDE_B).getNodeValue()),
                    new BigDecimal(attributes.getNamedItem(SIDE_C).getNodeValue())
            };
        }

        return result;
    }

    @DataProvider(name = "validNotExistTriangleXML")
    public static Object[][] getValidNotExistTriangleXMLData() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(new File(DATA_FILE_PATH));
        NodeList nodes = document.getElementsByTagName(NOT_EXISTS_TRIANGLE);
        Object[][] result = new BigDecimal[nodes.getLength()][];
        for (int i = 0; i < nodes.getLength(); i++) {
            NamedNodeMap attributes = nodes.item(i).getAttributes();
            result[i] = new BigDecimal[]{
                    new BigDecimal(attributes.getNamedItem(SIDE_A).getNodeValue()),
                    new BigDecimal(attributes.getNamedItem(SIDE_B).getNodeValue()),
                    new BigDecimal(attributes.getNamedItem(SIDE_C).getNodeValue())
            };
        }

        return result;
    }
}


