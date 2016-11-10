package task05.data;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Performs parsing XML document.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 10.11.2016
 */
public class XMLParser {

    /**
     * Parses input file and returns ready to use xml {@code Document} document.
     *
     * @param inputFile xml file to parse
     * @return parsed {@code Document} document
     * @throws ParserConfigurationException if error occurred while processing
     *                                      factory building
     * @throws IOException                  if error occurred with input file (not exists or no
     *                                      rights to read)
     * @throws SAXException                 general exception for XML parsing errors
     */
    public Document parseXMLDocument(File inputFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(inputFile);
    }
}
