package ge.chat.artmedia.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonDataReader {
    private static JsonNode testData;

    static {
        try (InputStream input = JsonDataReader.class.getClassLoader().getResourceAsStream("testdata.json")) {
            if (input == null) {
                throw new RuntimeException("testdata.json not found in resources folder");
            }
            ObjectMapper mapper = new ObjectMapper();
            testData = mapper.readTree(input);
        } catch (Exception e) {
            throw new RuntimeException("Error reading testdata.json", e);
        }
    }

    public static JsonNode get(String key) {
        return testData.get(key);
    }
}
