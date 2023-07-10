package com.econsult.health.mapper;

import com.econsult.health.dto.ExaminationDto;
import com.econsult.health.entity.Examination;
import com.econsult.health.entity.Patient;
import com.econsult.health.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class ExaminationMapperTest {

    @InjectMocks
    private final ExaminationMapper examinationMapper = Mappers.getMapper(ExaminationMapper.class);
    @Mock
    private PatientService patientService;
    private Examination examination;
    private ExaminationDto examinationDto;
    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = Patient.builder().id(1L).build();
        examination = Examination.builder()
                .id(1L)
                .patient(patient)
                .commonCode("testComm")
                .name("testName")
                .unitOfMeasure("testUnit")
                .result("testResult")
                .resultTime(LocalDateTime.of(2023, 10, 10, 12, 10))
                .commTime(LocalDateTime.of(2023, 10, 10, 12, 10))
                .resultHeaderName("testHeader")
                .build();

        examinationDto = ExaminationDto.builder()
                .id(1L)
                .patientId(1L)
                .commonCode("testComm")
                .name("testName")
                .unitOfMeasure("testUnit")
                .result("testResult")
                .resultTime(LocalDateTime.of(2023, 10, 10, 12, 10))
                .commTime(LocalDateTime.of(2023, 10, 10, 12, 10))
                .resultHeaderName("testHeader")
                .build();
    }

    @Test
    void toExaminationDto_getExamination_returnExaminationDto() {
        ExaminationDto actual = examinationMapper.toExaminationDto(examination);

        assertNotNull(actual);
        assertEquals(examinationDto.getId(), actual.getId());
        assertEquals(examinationDto.getPatientId(), actual.getPatientId());
        assertEquals(examinationDto.getCommonCode(), actual.getCommonCode());
        assertEquals(examinationDto.getName(), actual.getName());
        assertEquals(examinationDto.getUnitOfMeasure(), actual.getUnitOfMeasure());
        assertEquals(examinationDto.getResult(), actual.getResult());
        assertEquals(examinationDto.getResultTime(), actual.getResultTime());
        assertEquals(examinationDto.getCommTime(), actual.getCommTime());
        assertEquals(examinationDto.getResultTime(), actual.getResultTime());
        assertThat(actual).isInstanceOf(ExaminationDto.class);
    }

    @Test
    void toPatient_getPatientDto_returnPatient() {
        //given
        when(patientService.findById(1L)).thenReturn(patient);
        //when
        Examination actual = examinationMapper.toExamination(examinationDto);
        //then
        assertNotNull(actual);
        assertEquals(examination.getId(), actual.getId());
        assertEquals(examination.getPatient(), actual.getPatient());
        assertEquals(examination.getCommonCode(), actual.getCommonCode());
        assertEquals(examination.getName(), actual.getName());
        assertEquals(examination.getUnitOfMeasure(), actual.getUnitOfMeasure());
        assertEquals(examination.getResult(), actual.getResult());
        assertEquals(examination.getResultTime(), actual.getResultTime());
        assertEquals(examination.getCommTime(), actual.getCommTime());
        assertEquals(examination.getResultHeaderName(), actual.getResultHeaderName());
        assertThat(actual).isInstanceOf(Examination.class);
    }
}
