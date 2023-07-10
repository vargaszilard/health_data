package com.econsult.health.controller;

import com.econsult.health.dto.ExceptionResponse;
import com.econsult.health.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(Exception exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(getExceptionResponse(exception), HttpStatus.NOT_FOUND);
    }

    private ExceptionResponse getExceptionResponse(Exception exception) {
        return ExceptionResponse.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
