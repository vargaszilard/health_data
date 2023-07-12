package com.econsult.health.service;

import com.econsult.health.dto.ExaminationDto;

import java.util.List;

public interface ExaminationService {

    List<ExaminationDto> getAllExaminations();

    ExaminationDto getExaminationById(long id);

    ExaminationDto createExamination(ExaminationDto examinationDto);

    ExaminationDto updateExamination(Long examinationId, ExaminationDto examinationDto);

    void deleteExamination(Long id);

    List<ExaminationDto> createMultipleExaminations(ExaminationDto[] examinationDto);

    List<String> getResults(long patientId);
}
