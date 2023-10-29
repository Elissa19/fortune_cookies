package com.example.demo.exceptions.cookieexceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.example.demo.core.utils.Constants.COOKIE_NOT_FOUND_MESSAGE;

@ControllerAdvice
public class CookieExceptionHandler extends ResponseEntityExceptionHandler {
    @Data
    @AllArgsConstructor
    private static class CookieException {
        private String message;
    }
    @ExceptionHandler(NotFoundCookieException.class)
    protected ResponseEntity<CookieException> handleNotFoundCookieException() {
        return new ResponseEntity<>(new CookieException(COOKIE_NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND);
    }
}
