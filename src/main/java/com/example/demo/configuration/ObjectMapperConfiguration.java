package com.example.demo.configuration;

import com.example.demo.core.utils.JacksonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ObjectMapperConfiguration {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return JacksonUtils.createDefaultMapper();
    }
}
