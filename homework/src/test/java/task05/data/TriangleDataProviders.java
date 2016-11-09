package task05.data;

import org.testng.annotations.DataProvider;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import task05.Triangle;

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

    private static final String INVALID_TRIANGLES = "invalidTriangles";
    private static final String VALID_TRIANGLES = "validTriangles";

    private static final String COMMON_NODE = "common";
    private static final String EQUILATERAL_NODE = "equilateral";
    private static final String ISOSCELES_NODE = "isosceles";
    private static final String INVALID_NODE = "invalid";

    // XML DataProviders

    @DataProvider(name = "validXMLTriangle")
    public static Object[][] getValidTrianglesXMLData() throws Exception {
        File testDataFile = new File(DATA_FILE_PATH);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(testDataFile);
        Node validTriangleElement = document.getElementsByTagName(VALID_TRIANGLES).item(0);
        NodeList nodes = validTriangleElement.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            if (Node.TEXT_NODE == nodes.item(i).getNodeType()) {
                validTriangleElement.removeChild(nodes.item(i));
            }
        }

        Object[][] result = new Object[nodes.getLength()][];
        for (int i = 0; i < nodes.getLength(); i++) {
            NamedNodeMap attr = nodes.item(i).getAttributes();
            String nodeName = nodes.item(i).getNodeName();

            short triangleType;
            switch (nodeName) {
                case ISOSCELES_NODE:
                    triangleType = Triangle.TYPE_ISOSCELES;
                    break;
                case EQUILATERAL_NODE:
                    triangleType = Triangle.TYPE_EQUILATERAL;
                    break;
                default:
                    triangleType = Triangle.TYPE_COMMON;
                    break;
            }

            result[i] = new Object[]{
                    new BigDecimal(attr.getNamedItem(SIDE_A).getNodeValue()),
                    new BigDecimal(attr.getNamedItem(SIDE_B).getNodeValue()),
                    new BigDecimal(attr.getNamedItem(SIDE_C).getNodeValue()),
                    triangleType
            };
        }

        return result;
    }

    @DataProvider(name = "invalidXMLTriangle")
    public static Object[][] getInvalidTrianglesXMLData() throws Exception {
        File testDataFile = new File(DATA_FILE_PATH);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(testDataFile);
        NodeList nodes = document.getElementsByTagName(INVALID_NODE);
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
}


