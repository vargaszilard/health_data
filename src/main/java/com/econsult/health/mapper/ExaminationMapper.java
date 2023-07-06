package com.econsult.health.mapper;

import com.econsult.health.dto.ExaminationDto;
import com.econsult.health.entity.Examination;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExaminationMapper {

    ExaminationDto toExaminationDto(Examination examination);

    Examination toExamination(ExaminationDto examinationDto);

    List<ExaminationDto> toExaminationDtoList(List<Examination> examinations);
}
