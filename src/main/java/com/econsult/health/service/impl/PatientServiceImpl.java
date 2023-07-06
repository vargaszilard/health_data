package com.econsult.health.service.impl;

import com.econsult.health.entity.Patient;
import com.econsult.health.dto.PatientDto;
import com.econsult.health.mapper.PatientMapper;
import com.econsult.health.repository.PatientRepository;
import com.econsult.health.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patientMapper.toPatientDtoList(patients);
    }
}
