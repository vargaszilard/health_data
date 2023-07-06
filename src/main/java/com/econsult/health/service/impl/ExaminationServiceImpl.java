package com.econsult.health.service.impl;

import com.econsult.health.dto.ExaminationDto;
import com.econsult.health.entity.Examination;
import com.econsult.health.mapper.ExaminationMapper;
import com.econsult.health.repository.ExaminationRepository;
import com.econsult.health.service.ExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExaminationServiceImpl implements ExaminationService {

    private final ExaminationRepository examinationRepository;
    private final ExaminationMapper examinationMapper;

    @Override
    public List<ExaminationDto> getAllExaminations() {
        List<Examination> examinations = examinationRepository.findAll();
        return examinationMapper.toExaminationDtoList(examinations);
    }
}
