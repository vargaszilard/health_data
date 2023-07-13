package com.econsult.health.service.impl;

import com.econsult.health.dto.ExaminationDto;
import com.econsult.health.entity.Examination;
import com.econsult.health.exception.EntityNotFoundException;
import com.econsult.health.mapper.ExaminationMapper;
import com.econsult.health.repository.ExaminationRepository;
import com.econsult.health.service.ExaminationService;
import com.econsult.health.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * This service is responsible for managing {@link Examination} related data
 */
@Service
@RequiredArgsConstructor
public class ExaminationServiceImpl implements ExaminationService {

    private final ExaminationRepository examinationRepository;
    private final ExaminationMapper examinationMapper;
    private final PatientService patientService;

    /**
     * Returns all available Examination from the database
     * @return List of ExaminationDto
     */
    @Override
    public List<ExaminationDto> getAllExaminations() {
        List<Examination> examinations = examinationRepository.findAll();
        return examinationMapper.toExaminationDtoList(examinations);
    }

    /**
     * Returns an ExaminationDto with the given id
     * @param examinationId id of the Examination
     * @return an ExaminationDto with the given id
     * @throws EntityNotFoundException if the given id does not exist
     */
    @Override
    public ExaminationDto getExaminationById(long examinationId) {
        Examination examination = getExamination(examinationId);
        return examinationMapper.toExaminationDto(examination);
    }


    /**
     * Insert an Examination into the database
     * @param examinationDto ExaminationDto to be saved
     * @return Dto of the saved Examination
     */
    @Override
    public ExaminationDto createExamination(ExaminationDto examinationDto) {
        Examination savedExamination = examinationRepository.save(examinationMapper.toExamination(examinationDto));
        return examinationMapper.toExaminationDto(savedExamination);
    }

    /**
     * Update an existing Examination
     * @param examinationId id of the Examination to update
     * @param examinationDto for updating the Examination
     * @return Dto of the updated Examination object
     * @throws EntityNotFoundException if the given id does not exist
     */
    @Override
    public ExaminationDto updateExamination(Long examinationId, ExaminationDto examinationDto) {
        Examination updatedExamination = updateExaminationDto(examinationId, examinationDto);
        updatedExamination = examinationRepository.save(updatedExamination);
        return examinationMapper.toExaminationDto(updatedExamination);
    }

    /**
     * Delete an Examination with the given id
     * @param id id of the Examination to be deleted
     */
    @Override
    public void deleteExamination(Long id) {
        examinationRepository.deleteById(id);
    }

    /**
     * Saves multiple Examinations
     * @param examinationDto Array of ExaminationDtos
     * @return List of saved Examinations' dtos
     */
    @Override
    public List<ExaminationDto> createMultipleExaminations(ExaminationDto[] examinationDto) {
        List<Examination> examinations = examinationMapper.toExaminationList(Arrays.stream(examinationDto).toList());
        examinations = examinationRepository.saveAll(examinations);
        return examinationMapper.toExaminationDtoList(examinations);
    }

    /**
     * Gives the results of a Patient
     * @param patientId Id of the Patient
     * @return List of String containing the results
     * @throws EntityNotFoundException if the given Id not found
     */
    @Override
    public List<String> getResults(long patientId) {
        checkIfPatientExists(patientId);
        return examinationRepository.findResultsByPatientId(patientId);
    }

    /**
     * Gives the results of a Patient to a given commCode
     * @param patientId Id of the Patient
     * @param commCode Code of the comm
     * @return List of String containing the results
     * @throws EntityNotFoundException if the given Id not found
     */
    @Override
    public List<String> getResultsByCommCode(long patientId, String commCode) {
        checkIfPatientExists(patientId);
        return examinationRepository.findResultsByPatientIdAndCommCode(patientId, commCode);
    }

    private void checkIfPatientExists(long patientId) {
        if(!patientService.existPatientById(patientId)) {
            throw new EntityNotFoundException("Patient with id: " + patientId + " not found!");
        }
    }

    private Examination getExamination(long examinationId) {
        return examinationRepository.findById(examinationId)
                .orElseThrow(() -> new EntityNotFoundException("Examination with id: " + examinationId + " not found!"));
    }

    private Examination updateExaminationDto(Long examinationId, ExaminationDto examinationDto) {
        Examination updatedExamination = getExamination(examinationId);
        updatedExamination.setPatient(patientService.findById(examinationDto.getPatientId()));
        updatedExamination.setCommonCode(examinationDto.getCommonCode());
        updatedExamination.setName(examinationDto.getName());
        updatedExamination.setUnitOfMeasure(examinationDto.getUnitOfMeasure());
        updatedExamination.setResult(examinationDto.getResult());
        updatedExamination.setResultTime(examinationDto.getResultTime());
        updatedExamination.setCommTime(examinationDto.getCommTime());
        updatedExamination.setResultHeaderName(examinationDto.getResultHeaderName());
        return updatedExamination;
    }
}
