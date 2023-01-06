package com.deepspace.deanery.config;

import com.deepspace.deanery.exception.DeaneryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DeaneryException.class)
    public String handleDeaneryException(DeaneryException e, Model model) {
        /*log.error(e.getMessage());

        Map<String, Object> body = new HashMap<>(3);
        body.put("timestamp", OffsetDateTime.now(Clock.systemUTC()));
        body.put("message", e.getMessage());
        body.put("reason", e.getReason());
        return body;*/
        model.addAttribute("message", e.getMessage());
        return "sql-error";
    }

    @ExceptionHandler(SQLException.class)
    public String handlePSQLException(SQLException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "sql-error";
    }
}
