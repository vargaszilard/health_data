package com.econsult.health.mapper;

import com.econsult.health.entity.Patient;
import com.econsult.health.dto.PatientDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    // TODO: documentation, test

    PatientDto toPatientDto(Patient patient);

    Patient toPatient(PatientDto patientDto);

    List<PatientDto> toPatientDtoList(List<Patient> patients);

}
