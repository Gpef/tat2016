package io.reader;

import command.Command;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Performs reading commands from XML file. They must fit next template:
 * <commands>
 * <command name="${COMMAND_NAME}">
 * <${PROPERTY_NAME_1}>${PROPERTY_1}</${PROPERTY_NAME_1}>
 * <${PROPERTY_NAME_2}>${PROPERTY_2}</${PROPERTY_NAME_2}>
 * ...
 * <${PROPERTY_NAME_N}>${PROPERTY_N}</${PROPERTY_NAME_N}>
 * </command>
 * </commands>
 * <p>
 * Each command start with tag <command> in root tag <commands>.
 * Reads <command> tag and it's name attribute and all tags under it.
 * Makes properties map of read attributes.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public class XMLCommandReader implements CommandReader {
    private int count = 0;
    private List<HashMap<String, String>> commands;

    public XMLCommandReader(File file) throws IOException, ParserConfigurationException, SAXException {
        if (null == file) {
            throw new IOException("File path for XMLCommandReader is null");
        }
        commands = new Parser().parse(file);
    }

    /**
     * Checks, if there is any unread commands left.
     *
     * @return true - if there is one more command in xml file,
     * false - otherwise
     */
    @Override
    public boolean hasNext() {
        return commands.size() > count;
    }

    /**
     * Read next command in xml file.
     *
     * @return next read command. If next command doesn't exist
     * returns null.
     */
    @Override
    public HashMap<String, String> next() {
        if (hasNext()) {
            return commands.get(count++);
        }

        return null;
    }

    /**
     * Performs parsing each tag COMMAND_TAG and it's data in file to
     * list of property maps.
     */
    public class Parser {
        private final static String COMMAND_NAME_ATTRIBUTE = "name";
        private final static String COMMAND_TAG = "command";

        /**
         * Parses tag COMMAND_TAG, it's 'name' attribute and child nodes to
         * list of commands property maps.
         *
         * @param file xml file to parse
         * @return parsed list of maps with command names and args
         */
        public List<HashMap<String, String>> parse(File file) throws IOException {
            NodeList commandsNodes;
            try {
                commandsNodes = getCommandsNodes(file);
            } catch (SAXException | ParserConfigurationException e) {
                throw new IOException("Error parsing xml file for XMLCommandReader");
            }

            ArrayList<HashMap<String, String>> commands = new ArrayList<>();
            for (int i = 0; i < commandsNodes.getLength(); i++) {
                Node nextNode = commandsNodes.item(i);
                if (nextNode.getNodeName().equals(COMMAND_TAG)) {
                    commands.add(getPropertiesFromNode(nextNode));
                }
            }

            return commands;
        }

        /**
         * Collects command name as 'name' attribute and other properties
         * in tags inside {@code Node} node.
         * <p>
         * Returns map of command name and other properties with their keys and
         * values.
         *
         * @param node node to search properties
         * @return map of command properties (with it's name)
         */
        private HashMap<String, String> getPropertiesFromNode(Node node) {
            HashMap<String, String> nextCommand = new HashMap<>();
            NamedNodeMap attr = node.getAttributes();
            nextCommand.put(Command.PROPERTY_COMMAND_NAME,
                    (attr.getNamedItem(COMMAND_NAME_ATTRIBUTE) == null ?
                            "" : node.getAttributes().getNamedItem(COMMAND_NAME_ATTRIBUTE).getNodeValue()));
            NodeList argsNodes = node.getChildNodes();
            deleteTextNodes(argsNodes);
            for (int j = 0; j < argsNodes.getLength(); j++) {
                Node argNode = argsNodes.item(j);
                nextCommand.put(argNode.getNodeName(), argNode.getTextContent());
            }

            return nextCommand;
        }

        /**
         * Gets from xnl all nodes under 'commands' node with deleted
         * text nodes.
         *
         * @param file to get nodes
         * @return list of child nodes of root 'commands' node without text nodes
         * @throws ParserConfigurationException if error occurred while parsing xml
         * @throws IOException                  if error occurred while accessing file
         * @throws SAXException                 if error occurred while parsing xml
         */
        private NodeList getCommandsNodes(File file) throws ParserConfigurationException, IOException, SAXException {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            NodeList commandsNodes = documentBuilder.parse(file).getChildNodes().item(0).getChildNodes();
            deleteTextNodes(commandsNodes);
            return commandsNodes;
        }

        /**
         * Deletes Text nodes from node list.
         *
         * @param nodes node list to delete text
         */
        private void deleteTextNodes(NodeList nodes) {
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.TEXT_NODE) {
                    node.getParentNode().removeChild(node);
                }
            }
        }
    }
}
