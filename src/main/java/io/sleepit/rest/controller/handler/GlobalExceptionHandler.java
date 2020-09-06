package io.sleepit.rest.controller.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {IllegalStateException.class, IllegalArgumentException.class})
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<ErrorResult> handle(final Exception e) {
        return ResponseEntity.badRequest()
                .body(new ErrorResult(HttpStatus.BAD_REQUEST.name(), e.getMessage()));
    }

    public static class ErrorResult {

        private final String code;
        private final String description;

        public ErrorResult(final String code, final String description) {
            this.code = code;
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }

    }

}
