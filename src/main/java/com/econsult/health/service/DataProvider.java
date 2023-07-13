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

        Examination examination1 = createExamination(patient1, "com", "na", "cm",
                "10", LocalDateTime.of(2023, 10, 10, 0, 0), LocalDateTime.now(), "header");

        Examination examination2 = createExamination(patient2, "com2", "name2", "cm2",
                "12", LocalDateTime.of(2023, 10, 11, 0, 0), LocalDateTime.now(), "header2");

        Examination examination3 = createExamination(patient1, "com", "na", "cm",
                "13", LocalDateTime.of(2023, 10, 12, 0, 0), LocalDateTime.now(), "header");

        Examination examination4 = createExamination(patient1, "com", "na", "cm",
                "19", LocalDateTime.of(2023, 10, 13, 0, 0), LocalDateTime.now(), "header");

        Examination examination5 = createExamination(patient1, "com", "na", "cm",
                "14", LocalDateTime.of(2023, 10, 14, 0, 0), LocalDateTime.now(), "header");

        Examination examination6 = createExamination(patient1, "com", "nb", "cm",
                "15", LocalDateTime.of(2023, 9, 14, 0, 0), LocalDateTime.now(), "header");

        Examination examination7 = createExamination(patient1, "com", "nb", "cm",
                "16", LocalDateTime.of(2023, 9, 15, 0, 0), LocalDateTime.now(), "header");

        Examination examination8 = createExamination(patient1, "com", "nb", "cm",
                "10", LocalDateTime.of(2023, 9, 16, 0, 0), LocalDateTime.now(), "header");

        Examination examination10 = createExamination(patient1, "com", "nb", "cm",
                "10", LocalDateTime.of(2023, 9, 17, 0, 0), LocalDateTime.now(), "header");

        Examination examination11 = createExamination(patient1, "com", "nc", "cm",
                "10", LocalDateTime.of(2023, 8, 17, 0, 0), LocalDateTime.now(), "header");
        Examination examination9 = createExamination(patient1, "com", "nc", "cm",
                "11", LocalDateTime.of(2023, 8, 18, 0, 0), LocalDateTime.now(), "header");


        examinationRepository.save(examination1);
        examinationRepository.save(examination2);
        examinationRepository.save(examination3);
        examinationRepository.save(examination4);
        examinationRepository.save(examination5);
        examinationRepository.save(examination6);
        examinationRepository.save(examination7);
        examinationRepository.save(examination8);
        examinationRepository.save(examination9);
        examinationRepository.save(examination10);
        examinationRepository.save(examination11);
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
