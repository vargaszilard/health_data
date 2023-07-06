package com.econsult.health.service;

import com.econsult.health.dto.PatientDto;

import java.util.List;

public interface PatientService {

    List<PatientDto> getAllPatients();
}
