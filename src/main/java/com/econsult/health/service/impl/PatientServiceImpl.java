package com.econsult.health.service.impl;

import com.econsult.health.entity.Patient;
import com.econsult.health.dto.PatientDto;
import com.econsult.health.exception.EntityNotFoundException;
import com.econsult.health.mapper.PatientMapper;
import com.econsult.health.repository.PatientRepository;
import com.econsult.health.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    // TODO: documentation, test

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patientMapper.toPatientDtoList(patients);
    }

    @Override
    public Patient findById(long id) {
        return patientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Patient with id: " + id + " not found!")
        );
    }

    @Override
    public PatientDto getPatientById(long patientId) {
        return patientMapper.toPatientDto(findById(patientId));
    }

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient savedPatient = patientRepository.save(patientMapper.toPatient(patientDto));
        return patientMapper.toPatientDto(savedPatient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public PatientDto updatePatient(Long patientId, PatientDto patientDto) {
        PatientDto updatedPatientDto = updatePatientDto(patientId, patientDto);
        Patient updatedPatient = patientMapper.toPatient(updatedPatientDto);
        updatedPatient = patientRepository.save(updatedPatient);
        updatedPatient.setId(patientId);
        return patientMapper.toPatientDto(updatedPatient);
    }

    private PatientDto updatePatientDto(Long patientId, PatientDto patientDto) {
        PatientDto updatedPatientDto = getPatientById(patientId);
        updatedPatientDto.setFirstName(patientDto.getFirstName());
        updatedPatientDto.setLastName(patientDto.getLastName());
        updatedPatientDto.setBirthDate(patientDto.getBirthDate());
        updatedPatientDto.setSsn(patientDto.getSsn());
        updatedPatientDto.setSsnType(patientDto.getSsnType());
        updatedPatientDto.setSsnTypeDescription(patientDto.getSsnTypeDescription());
        updatedPatientDto.setMothersName(patientDto.getMothersName());
        return updatedPatientDto;
    }
}
