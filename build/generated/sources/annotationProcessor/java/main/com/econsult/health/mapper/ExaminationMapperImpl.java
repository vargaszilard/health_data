package com.econsult.health.mapper;

import com.econsult.health.dto.ExaminationDto;
import com.econsult.health.entity.Examination;
import com.econsult.health.entity.Patient;
import com.econsult.health.service.PatientService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-07T21:14:37+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Homebrew)"
)
@Component
public class ExaminationMapperImpl implements ExaminationMapper {

    @Autowired
    private PatientService patientService;

    @Override
    public ExaminationDto toExaminationDto(Examination examination) {
        if ( examination == null ) {
            return null;
        }

        long patientId = 0L;
        long id = 0L;
        String commonCode = null;
        String name = null;
        String unitOfMeasure = null;
        String result = null;
        LocalDateTime resultTime = null;
        LocalDateTime commTime = null;
        String resultHeaderName = null;

        Long id1 = examinationPatientId( examination );
        if ( id1 != null ) {
            patientId = id1;
        }
        if ( examination.getId() != null ) {
            id = examination.getId();
        }
        commonCode = examination.getCommonCode();
        name = examination.getName();
        unitOfMeasure = examination.getUnitOfMeasure();
        result = examination.getResult();
        resultTime = examination.getResultTime();
        commTime = examination.getCommTime();
        resultHeaderName = examination.getResultHeaderName();

        ExaminationDto examinationDto = new ExaminationDto( id, patientId, commonCode, name, unitOfMeasure, result, resultTime, commTime, resultHeaderName );

        return examinationDto;
    }

    @Override
    public List<ExaminationDto> toExaminationDtoList(List<Examination> examinations) {
        if ( examinations == null ) {
            return null;
        }

        List<ExaminationDto> list = new ArrayList<ExaminationDto>( examinations.size() );
        for ( Examination examination : examinations ) {
            list.add( toExaminationDto( examination ) );
        }

        return list;
    }

    @Override
    public Examination toExamination(ExaminationDto examinationDto) {
        if ( examinationDto == null ) {
            return null;
        }

        Examination.ExaminationBuilder examination = Examination.builder();

        examination.patient( patientService.findById( examinationDto.getPatientId() ) );
        examination.id( examinationDto.getId() );
        examination.commonCode( examinationDto.getCommonCode() );
        examination.name( examinationDto.getName() );
        examination.unitOfMeasure( examinationDto.getUnitOfMeasure() );
        examination.result( examinationDto.getResult() );
        examination.resultTime( examinationDto.getResultTime() );
        examination.commTime( examinationDto.getCommTime() );
        examination.resultHeaderName( examinationDto.getResultHeaderName() );

        return examination.build();
    }

    private Long examinationPatientId(Examination examination) {
        if ( examination == null ) {
            return null;
        }
        Patient patient = examination.getPatient();
        if ( patient == null ) {
            return null;
        }
        Long id = patient.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
