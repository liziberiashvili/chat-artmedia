package ge.chat.artmedia.utils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Utility class for reading property values from resource files.
 */
public class PropertiesReader {

    private static final String MAIN_RESOURCES_PATH = "src/main/resources/";
    private static final String TEST_RESOURCES_PATH = "src/test/resources/";

    /**
     * Reads the value of a given key from the specified properties file.
     *
     * @param path the path to the properties file
     * @param key  the key to read
     * @return the value associated with the key
     */
    public static String read(String path, String key) {
            Properties properties = new Properties();

            try (InputStream inputStream = Files.newInputStream(Paths.get(path))) {
                properties.load(inputStream);
            } catch (Exception e) {
                System.out.println("Exception reading " + path + ": " + e);
            }
            return properties.getProperty(key);
        }

    /**
     * Reads a key from testdata.properties in main resources.
     *
     * @param key the key to read
     * @return the value associated with the key
     */
    public static String readTestData(String key) {
        return read(MAIN_RESOURCES_PATH + "testdata.properties", key);
    }

    /**
     * Reads a key from config.properties in test resources.
     *
     * @param key the key to read
     * @return the value associated with the key
     */
    public static String readConfig(String key) {
        return read(TEST_RESOURCES_PATH + "config.properties", key);
    }

}
