package io.sleepit.rest.controller;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {IllegalStateException.class, IllegalArgumentException.class})
    @ResponseBody
    @Order(Integer.MIN_VALUE)
    public ResponseEntity<ErrorResult> handle(final Exception e) {
        return ResponseEntity.badRequest()
                .body(new ErrorResult(e.getMessage()));
    }

    public static class ErrorResult {

        private final String description;

        public ErrorResult(final String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }

    }

}
