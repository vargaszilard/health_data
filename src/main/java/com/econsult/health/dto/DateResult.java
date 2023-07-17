package com.econsult.health.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * This DTO exists to store the Result, ResultTime pairs
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DateResult {
    private LocalDateTime resultTime;
    private String result;
}
