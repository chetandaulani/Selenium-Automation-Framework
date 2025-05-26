package com.chetandaulani.utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    // Deserialize JSON file to List<T>
    public static <T> List<T> fromJsonFileToList(File file, TypeReference<List<T>> typeReference) throws IOException {
        return mapper.readValue(file, typeReference);
    }

    // Deserialize JSON string to List<T>
    public static <T> List<T> fromJsonStringToList(String json, TypeReference<List<T>> typeReference) throws JsonProcessingException {
        return mapper.readValue(json, typeReference);
    }

    // Serialize object to JSON string
    public static String toJsonString(Object object) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    // Write object to JSON file
    public static void toJsonFile(Object object, File file) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, object);
    }
}
