package task05.data;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Performs parsing and working with XML document.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 10.11.2016
 */
public class XMLParser {

    /**
     * Parses input file and returns ready to use xml document.
     *
     * @param inputFile xml file to parse
     * @return parsed {@code Document} document
     * @throws ParserConfigurationException if error occurred while processing
     *                                      factory building
     * @throws IOException                  if error occurred with input file (not exists or no
     *                                      rights to read)
     * @throws SAXException                 general exception for XML parsing errors
     */
    public Document getXMLDocument(File inputFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(inputFile);
    }

    /**
     * Removes all TEXT nodes from node list.
     *
     * @param nodeList list to find and remove text nodes
     */
    public void removeTextNodes(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (Node.TEXT_NODE == nodeList.item(i).getNodeType()) {
                nodeList.item(i).getParentNode().removeChild(nodeList.item(i));
            }
        }
    }
}
