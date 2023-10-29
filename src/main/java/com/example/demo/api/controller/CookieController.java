package com.example.demo.api.controller;

import com.example.demo.api.dto.CookieRequestDto;
import com.example.demo.core.service.CookieService;
import com.example.demo.core.model.Cookie;
import com.example.demo.core.utils.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.core.utils.Paths.RANDOMIZE;

@RestController
@RequiredArgsConstructor
public class CookieController {
    private final CookieService cookieService;

    @PostMapping(Paths.COOKIES)
    public Cookie createCookie(@RequestBody Cookie cookie) {
        return cookieService.createCookie(cookie);
    }

    @PutMapping(Paths.COOKIE_ID)
    public Cookie updateCookie(@PathVariable("id") Long id, @RequestBody CookieRequestDto cookieRequestDto) {
        cookieService.updateCookie(id, cookieRequestDto);
        return cookieService.getCookie(id);
    }

    @DeleteMapping(Paths.COOKIE_ID)
    public void deleteCookie(@PathVariable("id") Long id) {
        cookieService.deleteCookie(id);
    }

    @GetMapping(Paths.COOKIE_ID)
    @ResponseStatus(HttpStatus.OK)
    public Cookie getCookie(@PathVariable("id") Long id) {
        return cookieService.getCookie(id);
    }

    @GetMapping(Paths.COOKIES)
    public List<Cookie> getAllCookies() {
        return cookieService.getAllCookies();
    }

    @GetMapping(Paths.COOKIES + RANDOMIZE)
    public Cookie getRandomCookie() {
        return cookieService.getRandomCookie();
    }
}

