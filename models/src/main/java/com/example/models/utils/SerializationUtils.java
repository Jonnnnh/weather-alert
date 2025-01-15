package com.example.models.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serializeToJson(Object obj) throws JsonProcessingException {
        if (obj == null) {
            return null;
        }
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T deserializeFromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }
}
