package com.econsult.health.service.impl;

import com.econsult.health.entity.Patient;
import com.econsult.health.dto.PatientDto;
import com.econsult.health.exception.EntityNotFoundException;
import com.econsult.health.mapper.PatientMapper;
import com.econsult.health.repository.PatientRepository;
import com.econsult.health.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * This service is responsible for managing {@link Patient} related data
 */
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    /**
     * Returns all available Patient from the database
     * @return List of PatientDto
     */
    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patientMapper.toPatientDtoList(patients);
    }

    /**
     * Returns one Patient with the given id
     * @param id id of the Patient
     * @return a Patient with the given id
     * @throws EntityNotFoundException if the patient with id does not exist
     */
    @Override
    public Patient findById(long id) {
        return patientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Patient with id: " + id + " not found!")
        );
    }

    /**
     * Returns a PatientDto with the given id
     * @param patientId id of the patient
     * @return Dto of the Patient with the given id
     * @throws EntityNotFoundException if the given id does not exist
     */
    @Override
    public PatientDto getPatientById(long patientId) {
        return patientMapper.toPatientDto(findById(patientId));
    }

    /**
     * Insert a patient into the database
     * @param patientDto PatientDto to be saved
     * @return the saved PatientDto
     */
    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient savedPatient = patientRepository.save(patientMapper.toPatient(patientDto));
        return patientMapper.toPatientDto(savedPatient);
    }

    /**
     * Delete a Patient with the given id
     * @param id id of the Patient to be deleted
     */
    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    /**
     * Update an existing Patient
     * @param patientId id of the Patient to update
     * @param patientDto for updating the Patient
     * @return Dto of the updated PatientDto object
     * @throws EntityNotFoundException if the given id does not exist
     */
    @Override
    public PatientDto updatePatient(Long patientId, PatientDto patientDto) {
        Patient updatedPatient = updatePatientDto(patientId, patientDto);
        updatedPatient = patientRepository.save(updatedPatient);
        return patientMapper.toPatientDto(updatedPatient);
    }

    @Override
    public PatientDto findByBirthDateAndSsn(LocalDate birthDate, String ssn) {
        Patient patient = patientRepository.findByBirthDateAndSsn(birthDate, ssn).orElseThrow(
                () -> new EntityNotFoundException("Patient with BirthDate: " + birthDate + " and SSN: " + ssn + " not found")
        );
        return patientMapper.toPatientDto(patient);
    }

    @Override
    public boolean existPatientById(long patientId) {
        return patientRepository.existsById(patientId);
    }


    private Patient updatePatientDto(Long patientId, PatientDto patientDto) {
        Patient updatedPatient = findById(patientId);
        updatedPatient.setFirstName(patientDto.getFirstName());
        updatedPatient.setLastName(patientDto.getLastName());
        updatedPatient.setBirthDate(patientDto.getBirthDate());
        updatedPatient.setSsn(patientDto.getSsn());
        updatedPatient.setSsnType(patientDto.getSsnType());
        updatedPatient.setSsnTypeDescription(patientDto.getSsnTypeDescription());
        updatedPatient.setMothersName(patientDto.getMothersName());
        return updatedPatient;
    }
}
