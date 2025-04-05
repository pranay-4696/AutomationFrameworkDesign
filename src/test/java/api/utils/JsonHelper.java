package api.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class JsonHelper {

    public ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
        mapper.disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @SneakyThrows
    public <T> T getObjectFromString(String messageText, Class<T> classname) {
        return getMapper().readValue(messageText, classname);
    }

    public <T> List<T> getObjectsFromString(String messageText, Class<T> classname) throws Exception {
        CollectionType listType = getMapper().getTypeFactory()
                .constructCollectionType(ArrayList.class, classname);
        return getMapper().readValue(messageText, listType);
    }

    public <T> T getObjectFromJsonFile(String filePath, Class<T> classname) throws Exception {
        String jsonData = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        return getObjectFromString(jsonData, classname);
    }

    public <T> List<T> getObjectsFromJsonFile(String filePath, Class<T> classname) throws Exception {
        String jsonData = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        return getObjectsFromString(jsonData, classname);
    }
}