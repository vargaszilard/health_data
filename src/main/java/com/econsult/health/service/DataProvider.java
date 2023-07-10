package com.econsult.health.service;

import com.econsult.health.entity.Examination;
import com.econsult.health.entity.Patient;
import com.econsult.health.entity.enums.SsnType;
import com.econsult.health.repository.ExaminationRepository;
import com.econsult.health.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataProvider {
    // TODO: replace with data.sql, finish

    private final PatientRepository patientRepository;
    private final ExaminationRepository examinationRepository;

    @PostConstruct
    private void addData() {
        clearDatabase();

        Patient patient1 = createPatient("TempFirst", "TempLast", LocalDate.of(2023, 1, 1),
                "NA", SsnType.TAJ, "", "motherName");

        Patient patient2 = createPatient("TempFirst2", "TempLast2", LocalDate.of(2023, 1, 1),
                "NB", SsnType.EGYEB, "DIAK", "motherName2");

        patient1 = patientRepository.save(patient1);
        patient2 = patientRepository.save(patient2);

        Examination examination1 = createExamination(patient1, "com", "name", "cm",
                "10", LocalDateTime.now(), LocalDateTime.now(), "header");

        Examination examination2 = createExamination(patient2, "com2", "name2", "cm2",
                "12", LocalDateTime.now(), LocalDateTime.now(), "header2");


        examinationRepository.save(examination1);
        examinationRepository.save(examination2);
    }

    private void clearDatabase() {
        patientRepository.deleteAll();
        examinationRepository.deleteAll();
    }

    private Patient createPatient(String firstName, String lastName, LocalDate birthDate, String ssn, SsnType ssnType,
                                  String ssnTypeDescription, String mothersName) {
        return Patient.builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .ssn(ssn)
                .ssnType(ssnType)
                .ssnTypeDescription(ssnTypeDescription)
                .mothersName(mothersName)
                .build();
    }

    private Examination createExamination(Patient patient, String commonCode, String name, String unitOfMeasure,
                                          String result, LocalDateTime resultTime, LocalDateTime commTime,
                                          String resultHeaderName) {
        return Examination.builder()
                .patient(patient)
                .commonCode(commonCode)
                .name(name)
                .unitOfMeasure(unitOfMeasure)
                .result(result)
                .resultTime(resultTime)
                .commTime(commTime)
                .resultHeaderName(resultHeaderName)
                .build();
    }

}
