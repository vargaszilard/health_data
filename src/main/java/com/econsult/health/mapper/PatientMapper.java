package com.econsult.health.mapper;

import com.econsult.health.entity.Patient;
import com.econsult.health.dto.PatientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDto toPatientDto(Patient patient);

    @Mapping(target = "examinations", ignore = true)
    Patient toPatient(PatientDto patientDto);

    List<PatientDto> toPatientDtoList(List<Patient> patients);

}
