package com.econsult.health.mapper;

import com.econsult.health.dto.PatientDto;
import com.econsult.health.entity.Patient;
import com.econsult.health.entity.enums.SsnType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-07T21:14:38+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Homebrew)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientDto toPatientDto(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        Long id = null;
        String firstName = null;
        String lastName = null;
        LocalDate birthDate = null;
        String ssn = null;
        SsnType ssnType = null;
        String ssnTypeDescription = null;
        String mothersName = null;

        id = patient.getId();
        firstName = patient.getFirstName();
        lastName = patient.getLastName();
        birthDate = patient.getBirthDate();
        ssn = patient.getSsn();
        ssnType = patient.getSsnType();
        ssnTypeDescription = patient.getSsnTypeDescription();
        mothersName = patient.getMothersName();

        PatientDto patientDto = new PatientDto( id, firstName, lastName, birthDate, ssn, ssnType, ssnTypeDescription, mothersName );

        return patientDto;
    }

    @Override
    public Patient toPatient(PatientDto patientDto) {
        if ( patientDto == null ) {
            return null;
        }

        Patient.PatientBuilder patient = Patient.builder();

        patient.id( patientDto.getId() );
        patient.firstName( patientDto.getFirstName() );
        patient.lastName( patientDto.getLastName() );
        patient.birthDate( patientDto.getBirthDate() );
        patient.ssn( patientDto.getSsn() );
        patient.ssnType( patientDto.getSsnType() );
        patient.ssnTypeDescription( patientDto.getSsnTypeDescription() );
        patient.mothersName( patientDto.getMothersName() );

        return patient.build();
    }

    @Override
    public List<PatientDto> toPatientDtoList(List<Patient> patients) {
        if ( patients == null ) {
            return null;
        }

        List<PatientDto> list = new ArrayList<PatientDto>( patients.size() );
        for ( Patient patient : patients ) {
            list.add( toPatientDto( patient ) );
        }

        return list;
    }
}
