package com.example.demo.integration;

import com.example.demo.AbstractIntegrationTest;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.example.demo.core.utils.Constants.*;
import static com.example.demo.core.utils.FileProvider.readFromJson;
import static com.example.demo.core.utils.Paths.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CookieTests extends AbstractIntegrationTest {
    @Autowired
    WebTestClient webClient;

    @Test
    @DisplayName("Успешное создание печеньки")
    void createCookie() {
        webClient.post().uri(COOKIES)
                .contentType(APPLICATION_JSON)
                .bodyValue(readFromJson("cookie-body.json"))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath(ID).isNotEmpty()
                .jsonPath(DESCRIPTION).isEqualTo(EXPECTED_COOKIE_DESCRIPTION);
    }

    @Test
    @DisplayName("Получение печеньки по ID")
    void fetchCookieById() {
        Long id = createCookieId();
        webClient.get()
                .uri(COOKIE_ID, id)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath(ID).isEqualTo(id)
                .jsonPath(DESCRIPTION).isEqualTo(EXPECTED_COOKIE_DESCRIPTION);
    }

    @Test
    @DisplayName("Печенька не найден по ID")
    void notFoundCookieById() {
        webClient.get()
                .uri(COOKIE_ID, COOKIE_NON_EXIST_ID)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath(MESSAGE).isEqualTo(COOKIE_NOT_FOUND_MESSAGE);
    }

    @Test
    @DisplayName("Редактирование печеньки по ID")
    void updateCookieById() {
        Long id = createCookieId();
        webClient.put()
                .uri(COOKIE_ID, id)
                .contentType(APPLICATION_JSON)
                .bodyValue(readFromJson("update-cookie-body.json"))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath(ID).isEqualTo(id)
                .jsonPath(DESCRIPTION).isEqualTo(UPDATE_COOKIE_DESCRIPTION);
    }

    @Test
    @DisplayName("Редактирование несуществующей печеньки")
    void updateNonExistsCookieById() {
        webClient.delete()
                .uri(COOKIE_ID, COOKIE_NON_EXIST_ID)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath(MESSAGE).isEqualTo(COOKIE_NOT_FOUND_MESSAGE);
    }

    @Test
    @DisplayName("Получение всех печенек")
    void fetchAllCookie() {
        webClient.get()
                .uri(COOKIES)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[3].id").isNotEmpty()
                .jsonPath("$.[3].description").isNotEmpty();
    }

    @Test
    @DisplayName("Получение случайной печеньки")
    void fetchRandomCookie() {
        webClient.get()
                .uri(COOKIES + RANDOMIZE)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath(ID).isNotEmpty()
                .jsonPath(DESCRIPTION).isNotEmpty();
    }

    @Test
    @DisplayName("Удаление печеньки по ID")
    void deleteCookieById() {
        Long id = createCookieId();
        webClient.delete()
                .uri(COOKIE_ID, id)
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }

    @Test
    @DisplayName("Удаление несуществующей печеньки")
    void deleteNonExistsCookieById() {
        webClient.delete()
                .uri(COOKIE_ID, COOKIE_NON_EXIST_ID)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath(MESSAGE).isEqualTo(COOKIE_NOT_FOUND_MESSAGE);
    }

    private Long createCookieId() {
        var response = webClient.post().uri(COOKIES)
                .contentType(APPLICATION_JSON)
                .bodyValue(readFromJson("cookie-body.json"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).returnResult().getResponseBody();
        Integer id = JsonPath.read(response, ID);
        return Long.valueOf(id);
    }
}
