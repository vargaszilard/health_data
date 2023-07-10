package com.econsult.health.dto;

import com.econsult.health.entity.Patient;
import com.econsult.health.entity.enums.SsnType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * This DTO represents a patient. The purpose is to transfer data between layers.
 * <p>
 * For its entity see {@link Patient}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class PatientDto {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String ssn;
    private SsnType ssnType;
    private String ssnTypeDescription;
    private String mothersName;
}
