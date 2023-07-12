package com.econsult.health.mapper;

import com.econsult.health.dto.ExaminationDto;
import com.econsult.health.entity.Examination;
import com.econsult.health.service.PatientService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PatientService.class })
public interface ExaminationMapper {

    @Mapping(target = "patientId", source = "patient.id")
    ExaminationDto toExaminationDto(Examination examination);

    List<ExaminationDto> toExaminationDtoList(List<Examination> examinations);

    @Mapping(target = "patient", source = "patientId")
    Examination toExamination(ExaminationDto examinationDto);

    List<Examination> toExaminationList(List<ExaminationDto> examinationDtoList);
}
