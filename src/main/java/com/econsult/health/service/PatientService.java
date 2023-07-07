package com.econsult.health.service;

import com.econsult.health.entity.Patient;
import com.econsult.health.dto.PatientDto;

import java.util.List;

public interface PatientService {

    List<PatientDto> getAllPatients();

    Patient findById(long id);

    PatientDto getPatientById(long patientId);

    PatientDto createPatient(PatientDto patientDto);

    void deletePatient(Long id);

    PatientDto updatePatient(Long patientId, PatientDto patientDto);
}
