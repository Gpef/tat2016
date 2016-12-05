package autotests.utils.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Reads configuration options from properties file.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 29.11.2016
 */
public class PropertiesReader {

    /**
     * Get all properties from properties file with specified path.
     *
     * @param resourcePath path to .properties file
     * @return loaded properties
     */
    public static Properties getProperties(String resourcePath) {
        Properties properties = null;
        try {
            InputStream sourceStream = PropertiesReader.class.getClassLoader().getResourceAsStream(resourcePath);
            if (sourceStream != null) {
                properties = new Properties();
                properties.load(sourceStream);
            } else {
                throw new RuntimeException("There is no properties at specified path.");
            }
        } catch (IOException e) {
            System.out.println("Can't read properties file. Access or existing error");
        }

        return properties;
    }

    /**
     * Get properties with specified prefix from properties file with specified path.
     *
     * @param resourcePath path to .properties file
     * @param prefix       prefix to get properties with
     * @return loaded properties
     */
    public static Properties getPropertiesForPrefix(String resourcePath, String prefix) {
        Properties sourceProperties = getProperties(resourcePath);
        Properties properties = new Properties();
        sourceProperties.keySet().stream()
                .filter(Objects::nonNull)
                .map(Object::toString)
                .filter(key -> key.startsWith(prefix))
                .forEach(key -> properties.setProperty(key, sourceProperties.getProperty(key)));
        return properties;
    }

    /**
     * Get properties with specified prefix from resource file with specified path and
     * cuts this prefix in properties keys.
     *
     * @param resourcePath path to .properties file
     * @param prefix       prefix to get properties with
     * @return loaded properties without prefixes
     */
    public static Properties cutPropertiesForPrefix(String resourcePath, String prefix) {
        Properties sourceProperties = getProperties(resourcePath);
        Properties properties = new Properties();
        sourceProperties.keySet().stream()
                .filter(Objects::nonNull)
                .map(Object::toString)
                .filter(key -> key.startsWith(prefix))
                .forEach(key -> properties.setProperty(
                        key.substring(prefix.length()+1),
                        sourceProperties.getProperty(key))
                );
        return properties;
    }

    private PropertiesReader() {
    }
}
