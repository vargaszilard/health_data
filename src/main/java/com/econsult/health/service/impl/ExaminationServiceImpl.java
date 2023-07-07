package com.econsult.health.service.impl;

import com.econsult.health.dto.ExaminationDto;
import com.econsult.health.entity.Examination;
import com.econsult.health.exception.EntityNotFoundException;
import com.econsult.health.mapper.ExaminationMapper;
import com.econsult.health.repository.ExaminationRepository;
import com.econsult.health.service.ExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExaminationServiceImpl implements ExaminationService {
    // TODO: documentation, test

    private final ExaminationRepository examinationRepository;
    private final ExaminationMapper examinationMapper;

    @Override
    public List<ExaminationDto> getAllExaminations() {
        List<Examination> examinations = examinationRepository.findAll();
        return examinationMapper.toExaminationDtoList(examinations);
    }

    @Override
    public ExaminationDto getExaminationById(long examinationId) {
        Examination examination = examinationRepository.findById(examinationId)
                .orElseThrow(() -> new EntityNotFoundException("Examination with id: " + examinationId + " not found!"));
        return examinationMapper.toExaminationDto(examination);
    }

    @Override
    public ExaminationDto createExamination(ExaminationDto examinationDto) {
        Examination savedExamination = examinationRepository.save(examinationMapper.toExamination(examinationDto));
        return examinationMapper.toExaminationDto(savedExamination);
    }

    @Override
    public ExaminationDto updateExamination(Long examinationId, ExaminationDto examinationDto) {
        ExaminationDto updatedExaminationDto = updateExaminationDto(examinationId, examinationDto);
        Examination updatedExamination = examinationMapper.toExamination(updatedExaminationDto);
        updatedExamination = examinationRepository.save(updatedExamination);
        updatedExamination.setId(examinationId);
        return examinationMapper.toExaminationDto(updatedExamination);
    }

    @Override
    public void deleteExamination(Long id) {
        examinationRepository.deleteById(id);
    }

    private ExaminationDto updateExaminationDto(Long examinationId, ExaminationDto examinationDto) {
        ExaminationDto updatedExaminationDto = getExaminationById(examinationId);
        updatedExaminationDto.setPatientId(examinationDto.getPatientId());
        updatedExaminationDto.setCommonCode(examinationDto.getCommonCode());
        updatedExaminationDto.setName(examinationDto.getName());
        updatedExaminationDto.setUnitOfMeasure(examinationDto.getUnitOfMeasure());
        updatedExaminationDto.setResult(examinationDto.getResult());
        updatedExaminationDto.setResultTime(examinationDto.getResultTime());
        updatedExaminationDto.setCommTime(examinationDto.getCommTime());
        updatedExaminationDto.setResultHeaderName(examinationDto.getResultHeaderName());
        return updatedExaminationDto;
    }
}
