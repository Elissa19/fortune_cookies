package com.example.demo.exceptions.boredexceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.example.demo.core.utils.Constants.*;

@ControllerAdvice
public class BoredApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Data
    @AllArgsConstructor
    private static class BoredApiException {
        private String message;
    }
    @ExceptionHandler(NotFoundBoredApiException.class)
    protected ResponseEntity<BoredApiException> handleNotFoundBoredApiException() {
        return new ResponseEntity<>(
                new BoredApiExceptionHandler.BoredApiException(ACTIVITY_NOT_FOUND_MESSAGE),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnavialableBoredApiException.class)
    protected ResponseEntity<BoredApiException> handleUnavialableBoredApiException() {
        return new ResponseEntity<>(
                new BoredApiExceptionHandler.BoredApiException(EXTERNAL_SERVICE_IS_UNAVIALABLE),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestBoredApiException.class)
    protected ResponseEntity<BoredApiException> handleBadRequestBoredApiException() {
        return new ResponseEntity<>(
                new BoredApiExceptionHandler.BoredApiException(INCORRECT_REQUEST_PARAMS),
                HttpStatus.BAD_REQUEST);
    }
}
