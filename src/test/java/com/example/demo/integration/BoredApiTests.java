package com.example.demo.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.example.demo.core.utils.Constants.*;
import static com.example.demo.core.utils.Paths.RANDOM_ACTIVITY;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoredApiTests {
    @Autowired
    WebTestClient webClient;

    @ParameterizedTest(name = "{index} - успешное получение активности \"{0}\" для одного участника")
    @ValueSource(strings = {
            "education",
            "recreational",
            "social",
            "diy",
            "charity",
            "cooking",
            "relaxation",
            "music",
            "busywork"
    })
    void fetchActivityForOneParticipant(String type) {
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(RANDOM_ACTIVITY)
                        .queryParam(TYPE, type)
                        .queryParam(PARTICIPANTS, 1)
                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath(KEY).isNotEmpty()
                .jsonPath(ACTIVITY).isNotEmpty();
    }

    @Test
    @DisplayName("Получение активности с неверным параметром")
    void fetchActivityWithBadParameter() {
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(RANDOM_ACTIVITY)
                        .queryParam(TYPE, "music1")
                        .queryParam(PARTICIPANTS, 1)
                        .build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath(MESSAGE).isEqualTo(INCORRECT_REQUEST_PARAMS);
    }

    @Test
    @DisplayName("Получение несуществуюшей активности")
    void fetchNonExistsActivity() {
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(RANDOM_ACTIVITY)
                        .queryParam(TYPE, MUSIC)
                        .queryParam(PARTICIPANTS, 10)
                        .build())
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath(MESSAGE).isEqualTo(ACTIVITY_NOT_FOUND_MESSAGE);
    }
}
