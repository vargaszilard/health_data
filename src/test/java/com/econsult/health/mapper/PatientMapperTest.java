package com.econsult.health.mapper;

import com.econsult.health.dto.PatientDto;
import com.econsult.health.entity.Patient;
import com.econsult.health.entity.enums.SsnType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PatientMapperTest {

    @InjectMocks
    private final PatientMapper patientMapper = Mappers.getMapper(PatientMapper.class);
    private Patient patient;
    private PatientDto patientDto;

    @BeforeEach
    void setUp() {
        patient = Patient.builder()
                .id(1L)
                .firstName("testFirst")
                .lastName("testLast")
                .birthDate(LocalDate.of(1998, 6, 13))
                .ssn("NA")
                .ssnType(SsnType.TAJ)
                .ssnTypeDescription("")
                .mothersName("testMothersName")
                .build();

        patientDto = PatientDto.builder()
                .id(1L)
                .firstName("testFirst")
                .lastName("testLast")
                .birthDate(LocalDate.of(1998, 6, 13))
                .ssn("NA")
                .ssnType(SsnType.TAJ)
                .ssnTypeDescription("")
                .mothersName("testMothersName")
                .build();
    }

    @Test
    void toPatientDto_getPatient_returnPatientDto() {
        PatientDto actual = patientMapper.toPatientDto(patient);

        assertNotNull(actual);
        assertEquals(patientDto.getId(), actual.getId());
        assertEquals(patientDto.getFirstName(), actual.getFirstName());
        assertEquals(patientDto.getLastName(), actual.getLastName());
        assertEquals(patientDto.getBirthDate(), actual.getBirthDate());
        assertEquals(patientDto.getSsn(), actual.getSsn());
        assertEquals(patientDto.getSsnType(), actual.getSsnType());
        assertEquals(patientDto.getSsnTypeDescription(), actual.getSsnTypeDescription());
        assertEquals(patientDto.getMothersName(), actual.getMothersName());
        assertThat(actual).isInstanceOf(PatientDto.class);
    }

    @Test
    void toPatient_getPatientDto_returnPatient() {
        Patient actual = patientMapper.toPatient(patientDto);

        assertNotNull(actual);
        assertEquals(patient.getId(), actual.getId());
        assertEquals(patient.getFirstName(), actual.getFirstName());
        assertEquals(patient.getLastName(), actual.getLastName());
        assertEquals(patient.getBirthDate(), actual.getBirthDate());
        assertEquals(patient.getSsn(), actual.getSsn());
        assertEquals(patient.getSsnType(), actual.getSsnType());
        assertEquals(patient.getSsnTypeDescription(), actual.getSsnTypeDescription());
        assertEquals(patient.getMothersName(), actual.getMothersName());
        assertThat(actual).isInstanceOf(Patient.class);
    }
}
