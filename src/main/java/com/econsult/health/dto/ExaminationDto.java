package com.econsult.health.dto;

import com.econsult.health.entity.Examination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * This DTO represents an examination. The purpose is to transfer data between layers.
 * <p>
 * For its entity see {@link Examination}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class ExaminationDto {

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
