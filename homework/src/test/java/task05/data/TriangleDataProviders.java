package task05.data;

import org.testng.annotations.DataProvider;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import task05.Triangle;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

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
    private static final String TYPE = "type";

    private static final String INVALID_NODE = "invalid";
    private static final String VALID_NODE = "valid";


    private static final String COMMON_TYPE = "common";
    private static final String EQUILATERAL_TYPE = "equilateral";
    private static final String ISOSCELES_TYPE = "isosceles";


    @DataProvider(name = "validXMLTriangle")
    public static Object[][] getValidTrianglesXMLData() throws Exception {
        return readXMLTriangleData(VALID_NODE);
    }

    @DataProvider(name = "invalidXMLTriangle")
    public static Object[][] getInvalidTrianglesXMLData() throws Exception {
        return readXMLTriangleData(INVALID_NODE);
    }

    /**
     * Reads all necessary attributes from tag with name equals
     * {@code String} nodeNameToRead and creates 2d array of these attributes values.
     *
     * @param nodeNameToRead tag name to find attributes
     * @return filled 2d array of attribute values
     * @throws Exception if error occurred while reading file
     *                   (IOException) or parsing it (SAXException)
     */
    private static Object[][] readXMLTriangleData(String nodeNameToRead) throws Exception {
        XMLParser xmlParser = new XMLParser();
        Document document = xmlParser.parseXMLDocument(new File(DATA_FILE_PATH));
        NodeList nodes = document.getElementsByTagName(nodeNameToRead);

        Object[][] result = new Object[nodes.getLength()][];
        for (int i = 0; i < nodes.getLength(); i++) {
            result[i] = fillTriangleAttrArray(nodes.item(i).getAttributes());
        }
        return result;
    }

    /**
     * Reads triangle sides and type (if present) from node attributes and
     * creates array with triangle sides.
     *
     * @param attr node attributes to find sides and type
     * @return filled array of triangle sides and type
     * (if present in attributes)
     */
    private static Object[] fillTriangleAttrArray(NamedNodeMap attr) {
        ArrayList<Object> attrList = new ArrayList<>();
        attrList.add(attr.getNamedItem(SIDE_A) == null ?
                null : new BigDecimal(attr.getNamedItem(SIDE_A).getNodeValue()));
        attrList.add(attr.getNamedItem(SIDE_B) == null ?
                null : new BigDecimal(attr.getNamedItem(SIDE_B).getNodeValue()));
        attrList.add(attr.getNamedItem(SIDE_C) == null ?
                null : new BigDecimal(attr.getNamedItem(SIDE_C).getNodeValue()));

        if (attr.getNamedItem(TYPE) != null) {
            String triangleTypeAttr = attr.getNamedItem(TYPE).getNodeValue();
            switch (triangleTypeAttr) {
                case ISOSCELES_TYPE:
                    attrList.add(Triangle.TYPE_ISOSCELES);
                    break;
                case EQUILATERAL_TYPE:
                    attrList.add(Triangle.TYPE_EQUILATERAL);
                    break;
                default:
                    attrList.add(Triangle.TYPE_COMMON);
                    break;
            }
        }

        return attrList.toArray();
    }
}


