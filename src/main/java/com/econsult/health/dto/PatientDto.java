package com.econsult.health.dto;

import com.econsult.health.entity.enums.SsnType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PatientDto {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String ssn;
    private SsnType ssnType;
    private String ssnTypeDescription;
    private String mothersName;
}
