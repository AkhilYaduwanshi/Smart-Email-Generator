package com.chatbot.gemini.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeminiApiException.class)
    public ResponseEntity<Map<String, Object>> handleGeminiApiException(GeminiApiException ex) {

        Map<String, Object> error = Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error", "Gemini API Error",
                "message", ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {

        Map<String, Object> error = Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error", "Internal Server Error",
                "message", ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}