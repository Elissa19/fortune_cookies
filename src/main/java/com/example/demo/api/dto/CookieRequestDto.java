package com.example.demo.api.dto;

import jakarta.validation.constraints.NotNull;

/**
 * DTO запроса на предсказание.
 *
 * @param description Текст предсказания.
 */
public record CookieRequestDto (
        @NotNull
        String description) {
}
