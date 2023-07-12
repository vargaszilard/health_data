package com.econsult.health.service;

import com.econsult.health.dto.ExaminationDto;
import com.econsult.health.entity.Examination;
import com.econsult.health.entity.Patient;
import com.econsult.health.exception.EntityNotFoundException;
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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    void getExaminationById_idDoesNotExist_throwsException() {
        //given
        //when
        //then
        assertThatThrownBy(() -> examinationService.getExaminationById(1L)).isInstanceOf(EntityNotFoundException.class);
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

    @Test
    void createMultipleExaminations_validExaminations_returnSavedExaminations() {
        //given
        Examination examination1 = createExamination(1L, "testName");
        Examination examination2 = createExamination(1L, "testName");
        ExaminationDto examinationDto1 = createExaminationDto(1L, 1L, "testName");
        ExaminationDto examinationDto2 = createExaminationDto(1L, 1L, "testName");
        ExaminationDto[] examinationDtos = {examinationDto1, examinationDto2};
        List<ExaminationDto> examinationDtoList = List.of(examinationDto1, examinationDto2);
        List<Examination> examinationList = List.of(examination1, examination2);
        when(examinationRepository.saveAll(examinationList)).thenReturn(examinationList);
        when(examinationMapper.toExaminationList(examinationDtoList)).thenReturn(examinationList);
        when(examinationMapper.toExaminationDtoList(examinationList)).thenReturn(examinationDtoList);
        //when
        List<ExaminationDto> result = examinationService.createMultipleExaminations(examinationDtos);
        //then
        assertEquals(examinationDtoList, result);
    }

    @Test
    void getResults_existingId_returnOkAndResults() {
        //given
        long id = 1L;
        List<String> expected = List.of("10", "12");
        when(patientService.existPatientById(id)).thenReturn(true);
        when(examinationRepository.findResultsByPatientId(id)).thenReturn(expected);
        //when
        List<String> result = examinationService.getResults(id);
        //then
        assertEquals(2, result.size());
        assertTrue(result.contains("10"));
        assertTrue(result.contains("12"));
    }

    @Test
    void getResults_idDoesNotExist_throwsException() {
        //given
        //when
        //then
        assertThatThrownBy(() -> examinationService.getResults(1L)).isInstanceOf(EntityNotFoundException.class);
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
