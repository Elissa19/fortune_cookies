package com.example.demo.core.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;

import java.util.TimeZone;

@UtilityClass
public class JacksonUtils {
    private ObjectMapper defaultObjectMapper;

    public static ObjectMapper createDefaultMapper() {
        return defaultObjectMapper = defaultObjectMapper == null
                ? new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
                .disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)
                .disable(SerializationFeature.WRITE_DATES_WITH_CONTEXT_TIME_ZONE)
                .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                .setTimeZone(TimeZone.getDefault())
                : defaultObjectMapper;
    }
}
