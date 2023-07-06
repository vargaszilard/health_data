package com.econsult.health.service;

import com.econsult.health.dto.ExaminationDto;

import java.util.List;

public interface ExaminationService {

    List<ExaminationDto> getAllExaminations();
}
