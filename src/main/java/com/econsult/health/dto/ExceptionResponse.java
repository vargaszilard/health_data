package com.econsult.health.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ExceptionResponse {
    private String message;
    private LocalDateTime timestamp;
}
