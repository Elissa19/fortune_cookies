package com.example.demo.core.utils;

import jakarta.validation.constraints.NotNull;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileProvider {
    private static final String JSON_FILES_PATH = "src/test/resources/json";
    @SneakyThrows
    public static String readFromJson(@NotNull String fileName) {
        return Files.readString(Paths.get(JSON_FILES_PATH, fileName));
    }
}
