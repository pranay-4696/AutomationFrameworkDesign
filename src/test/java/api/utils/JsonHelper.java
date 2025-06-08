package api.utils;

import api.exceptions.UtilsException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    private final ObjectMapper mapper;

    public JsonHelper() {
        this.mapper = createMapper();
    }

    private ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
        mapper.disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public <T> T getObjectFromString(String jsonString, Class<T> targetClass) {
        try {
            return mapper.readValue(jsonString, targetClass);
        } catch (Exception exception) {
            throw new UtilsException("Failed to parse object from JSON string: " + jsonString, exception);
        }
    }

    public <T> List<T> getObjectsFromString(String jsonString, Class<T> targetClass) {
        try {
            CollectionType listType = mapper.getTypeFactory()
                    .constructCollectionType(ArrayList.class, targetClass);
            return mapper.readValue(jsonString, listType);
        } catch (Exception exception) {
            throw new UtilsException("Failed to parse list of objects from JSON string: " + jsonString, exception);
        }
    }

    public <T> T getObjectFromJsonFile(String filePath, Class<T> targetClass) {
        try {
            String jsonData = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
            return getObjectFromString(jsonData, targetClass);
        } catch (Exception exception) {
            throw new UtilsException("Failed to parse object from JSON file: " + filePath, exception);
        }
    }

    public <T> List<T> getObjectsFromJsonFile(String filePath, Class<T> targetClass) {
        try {
            String jsonData = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
            return getObjectsFromString(jsonData, targetClass);
        } catch (Exception exception) {
            throw new UtilsException("Failed to parse list of objects from JSON file: " + filePath, exception);
        }
    }
}
