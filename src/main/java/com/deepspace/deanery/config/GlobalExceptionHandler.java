package com.deepspace.deanery.config;

import com.deepspace.deanery.exception.DeaneryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DeaneryException.class)
    public Map<String, Object> handleDeaneryException(DeaneryException e) {
        log.error(e.getMessage());

        Map<String, Object> body = new HashMap<>(3);
        body.put("timestamp", OffsetDateTime.now(Clock.systemUTC()));
        body.put("message", e.getMessage());
        body.put("reason", e.getReason());
        return body;
    }
}
