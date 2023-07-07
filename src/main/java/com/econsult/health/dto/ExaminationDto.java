package com.econsult.health.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ExaminationDto {
    // TODO: documentation

    private long id;
    private long patientId;
    private String commonCode;
    private String name;
    private String unitOfMeasure;
    private String result;
    private LocalDateTime resultTime;
    private LocalDateTime commTime;
    private String resultHeaderName;

}
