package com.example.demo.core.service;

import com.example.demo.api.dto.CookieRequestDto;
import com.example.demo.core.model.Cookie;
import com.example.demo.exceptions.cookieexceptions.NotFoundCookieException;
import com.example.demo.repository.CookieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CookieService {
    private final CookieRepository cookieRepository;
    public Cookie getCookie(Long id) {
        if (cookieRepository.findById(id).isEmpty()) {
            throw new NotFoundCookieException();
        }
            return cookieRepository.findById(id).orElseThrow();
    }

    public List<Cookie> getAllCookies() {
        return cookieRepository.findAll();
    }

    public Cookie getRandomCookie() {
        return cookieRepository.getRandom();
    }

    public Cookie createCookie(Cookie cookie) {
        cookie.setCreationDate(LocalDateTime.now());
        return cookieRepository.save(cookie);
    }

    public void updateCookie(Long id, CookieRequestDto cookieRequestDto) {
        cookieRepository.update(id, cookieRequestDto.description());
    }

    public void deleteCookie(Long id) {
        if (cookieRepository.findById(id).isEmpty()) {
            throw new NotFoundCookieException();
        }
        cookieRepository.deleteById(id);
    }
}
