package task05.data;

import org.testng.annotations.DataProvider;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import task05.Triangle;

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

    @DataProvider(name = "validXMLTriangle")
    public static Object[][] getValidTrianglesXMLData() throws Exception {
        XMLParser xmlParser = new XMLParser();
        Document document = xmlParser.getXMLDocument(new File(DATA_FILE_PATH));
        Node validTriangleParentNode = document.getElementsByTagName(VALID_TRIANGLES).item(0);
        NodeList nodes = validTriangleParentNode.getChildNodes();
        xmlParser.removeTextNodes(nodes);

        Object[][] result = new Object[nodes.getLength()][];
        for (int i = 0; i < nodes.getLength(); i++) {
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

            result[i] = fillTriangleArray(nodes.item(i).getAttributes(), triangleType);
        }

        return result;
    }

    @DataProvider(name = "invalidXMLTriangle")
    public static Object[][] getInvalidTrianglesXMLData() throws Exception {
        XMLParser xmlParser = new XMLParser();
        Document document = xmlParser.getXMLDocument(new File(DATA_FILE_PATH));
        NodeList nodes = document.getElementsByTagName(INVALID_NODE);

        Object[][] result = new BigDecimal[nodes.getLength()][];
        for (int i = 0; i < nodes.getLength(); i++) {
            result[i] = fillTriangleArray(nodes.item(i).getAttributes());
        }

        return result;
    }

    /**
     * Reads triangle sides from node attributes and
     * creates array with triangle sides.
     *
     * @param attr node attributes to find sides
     * @return filled array of triangle sides
     */
    private static Object[] fillTriangleArray(NamedNodeMap attr) {
        return new BigDecimal[]{
                attr.getNamedItem(SIDE_A) == null ? null : new BigDecimal(attr.getNamedItem(SIDE_A).getNodeValue()),
                attr.getNamedItem(SIDE_B) == null ? null : new BigDecimal(attr.getNamedItem(SIDE_B).getNodeValue()),
                attr.getNamedItem(SIDE_C) == null ? null : new BigDecimal(attr.getNamedItem(SIDE_C).getNodeValue())};
    }

    /**
     * Reads triangle sides from node attributes and
     * creates array with triangle sides and it's type.
     *
     * @param attr         node attributes to find sides
     * @param triangleType triangle's type value assigned to this
     *                     triangle types
     * @return filled array of triangle sides
     */
    private static Object[] fillTriangleArray(NamedNodeMap attr, short triangleType) {
        return new Object[]{
                attr.getNamedItem(SIDE_A) == null ? null : new BigDecimal(attr.getNamedItem(SIDE_A).getNodeValue()),
                attr.getNamedItem(SIDE_B) == null ? null : new BigDecimal(attr.getNamedItem(SIDE_B).getNodeValue()),
                attr.getNamedItem(SIDE_C) == null ? null : new BigDecimal(attr.getNamedItem(SIDE_C).getNodeValue()),
                triangleType};
    }
}


