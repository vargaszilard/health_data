package com.econsult.health.service;

import com.econsult.health.dto.ExaminationDto;
import com.econsult.health.entity.Examination;
import com.econsult.health.entity.Patient;
import com.econsult.health.mapper.ExaminationMapper;
import com.econsult.health.repository.ExaminationRepository;
import com.econsult.health.service.impl.ExaminationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminationServiceTest {

    @InjectMocks
    private ExaminationServiceImpl examinationService;
    @Mock
    private ExaminationRepository examinationRepository;
    @Mock
    private ExaminationMapper examinationMapper;
    @Mock
    private PatientService patientService;

    @Test
    void getAllExaminations_examinationsExist_returnExaminationDtos() {
        //given
        Examination examination1 = createExamination(1L, "testName");
        Examination examination2 = createExamination(2L, "testName2");
        ExaminationDto examinationDto1 = createExaminationDto(1L, 1L, "testName");
        ExaminationDto examinationDto2 = createExaminationDto(2L, 1L, "testName2");
        List<Examination> examinations = List.of(examination1, examination2);
        List<ExaminationDto> examinationDtos = List.of(examinationDto1, examinationDto2);
        when(examinationRepository.findAll()).thenReturn(examinations);
        when(examinationMapper.toExaminationDtoList(examinations)).thenReturn(examinationDtos);
        //when
        List<ExaminationDto> result = examinationService.getAllExaminations();
        //then
        assertTrue(result.contains(examinationDto1));
        assertTrue(result.contains(examinationDto2));
    }

    @Test
    void getExaminationById_idExists_returnExaminationDto() {
        //given
        Examination examination = createExamination(1L, "testName");
        ExaminationDto expected = createExaminationDto(1L, 1L, "testName");
        when(examinationRepository.findById(1L)).thenReturn(Optional.of(examination));
        when(examinationMapper.toExaminationDto(examination)).thenReturn(expected);
        //when
        ExaminationDto result = examinationService.getExaminationById(1L);
        //then
        assertEquals(expected, result);
    }

    @Test
    void createExamination_validExamination_returnExaminationDto() {
        //given
        Examination examination = createExamination(1L, "testName");
        ExaminationDto expected = createExaminationDto(1L, 1L, "testName");
        when(examinationRepository.save(examination)).thenReturn(examination);
        when(examinationMapper.toExamination(expected)).thenReturn(examination);
        when(examinationMapper.toExaminationDto(examination)).thenReturn(expected);
        //when
        ExaminationDto result = examinationService.createExamination(expected);
        //then
        assertEquals(expected, result);
    }

    @Test
    void updateExamination_validExamination_returnUpdatedExamination() {
        //given
        long id = 1L;
        ExaminationDto updatedExaminationDto = createExaminationDto(id, id, "testNameUpdated");
        Examination updatedExamination = createExamination(id, "testNameUpdated");
        Examination examination = createExamination(id, "testName");
        when(examinationRepository.findById(id)).thenReturn(Optional.of(examination));
        when(examinationRepository.save(updatedExamination)).thenReturn(updatedExamination);
        when(examinationMapper.toExaminationDto(updatedExamination)).thenReturn(updatedExaminationDto);
        when(patientService.findById(1L)).thenReturn(Patient.builder().id(id).build());
        //when
        ExaminationDto result = examinationService.updateExamination(id, updatedExaminationDto);
        //then
        assertEquals(updatedExaminationDto, result);
    }

    private ExaminationDto createExaminationDto(long id, long patientId,  String name) {
        return ExaminationDto.builder()
                .id(id)
                .patientId(patientId)
                .name(name)
                .build();
    }

    private Examination createExamination(long id, String name) {
        return Examination.builder()
                .id(id)
                .name(name)
                .build();
    }
}
