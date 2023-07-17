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
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataProvider {

    private final PatientRepository patientRepository;
    private final ExaminationRepository examinationRepository;

    @PostConstruct
    private void addData() {
        clearDatabase();

        Patient patient1 = createPatient("Grace", "Welch", LocalDate.of(2002, 7, 12),
                "11732425674", SsnType.TAJ, "", "Dora Smith");

        Patient patient2 = createPatient("Marshall", "Lynn", LocalDate.of(1998, 9, 16),
                "1756238JE", SsnType.SZEMELY_IG, "", "Sophie Turner");

        Patient patient3 = createPatient("Cynthia", "Dominguez", LocalDate.of(1992, 11, 8),
                "175623833", SsnType.UTLEVELSZAM, "", "Charlotte Diaz");

        Patient patient4 = createPatient("Marcia", "Brown", LocalDate.of(2004, 3, 11),
                "175633525", SsnType.EGYEB, "DIAK", "Katarina Kiss");

        Patient patient5 = createPatient("Karina", "Conner", LocalDate.of(2001, 12, 6),
                "1248743875", SsnType.TAJ, "", "Olivia Schumacher");

        patient1 = patientRepository.save(patient1);
        patient2 = patientRepository.save(patient2);
        patient3 = patientRepository.save(patient3);
        patient4 = patientRepository.save(patient4);
        patient5 = patientRepository.save(patient5);

        Examination examination1 = createExamination(patient1, "com", "na", "cm",
                "10", LocalDateTime.of(2023, 10, 10, 0, 0));
        Examination examination2 = createExamination(patient1, "com", "na", "cm",
                "13", LocalDateTime.of(2023, 10, 12, 0, 0));
        Examination examination3 = createExamination(patient1, "com", "na", "cm",
                "19", LocalDateTime.of(2023, 10, 13, 0, 0));
        Examination examination4 = createExamination(patient1, "com", "na", "cm",
                "14", LocalDateTime.of(2023, 10, 14, 0, 0));
        Examination examination5 = createExamination(patient1, "com", "nb", "cm",
                "15", LocalDateTime.of(2023, 9, 14, 0, 0));
        Examination examination6 = createExamination(patient1, "com", "nb", "cm",
                "16", LocalDateTime.of(2023, 9, 15, 0, 0));
        Examination examination7 = createExamination(patient1, "com", "nb", "cm",
                "10", LocalDateTime.of(2023, 9, 16, 0, 0));
        Examination examination8 = createExamination(patient1, "com", "nc", "cm",
                "11", LocalDateTime.of(2023, 8, 18, 0, 0));
        Examination examination9 = createExamination(patient1, "com", "nb", "cm",
                "10", LocalDateTime.of(2023, 9, 17, 0, 0));
        Examination examination10 = createExamination(patient1, "com", "nb", "cm",
                "15", LocalDateTime.of(2023, 9, 17, 0, 0));

        Examination examination11 = createExamination(patient2, "com", "na", "cm",
                "10", LocalDateTime.of(2023, 10, 10, 0, 0));
        Examination examination12 = createExamination(patient2, "com", "na", "cm",
                "13", LocalDateTime.of(2023, 10, 12, 0, 0));
        Examination examination13 = createExamination(patient2, "com", "na", "cm",
                "19", LocalDateTime.of(2023, 10, 13, 0, 0));
        Examination examination14 = createExamination(patient2, "com", "na", "cm",
                "14", LocalDateTime.of(2023, 10, 14, 0, 0));
        Examination examination15 = createExamination(patient2, "com", "nb", "cm",
                "15", LocalDateTime.of(2023, 9, 14, 0, 0));
        Examination examination16 = createExamination(patient2, "com", "nb", "cm",
                "16", LocalDateTime.of(2023, 9, 15, 0, 0));
        Examination examination17 = createExamination(patient2, "com", "nb", "cm",
                "10", LocalDateTime.of(2023, 9, 16, 0, 0));
        Examination examination18 = createExamination(patient2, "com", "nc", "cm",
                "11", LocalDateTime.of(2023, 8, 18, 0, 0));
        Examination examination19 = createExamination(patient2, "com", "nb", "cm",
                "10", LocalDateTime.of(2023, 9, 17, 0, 0));
        Examination examination20 = createExamination(patient2, "com", "nb", "cm",
                "15", LocalDateTime.of(2023, 9, 17, 0, 0));

        Examination examination21 = createExamination(patient3, "com", "na", "cm",
                "10", LocalDateTime.of(2023, 10, 10, 0, 0));
        Examination examination22 = createExamination(patient3, "com", "na", "cm",
                "13", LocalDateTime.of(2023, 10, 12, 0, 0));
        Examination examination23 = createExamination(patient3, "com", "na", "cm",
                "19", LocalDateTime.of(2023, 10, 13, 0, 0));
        Examination examination24 = createExamination(patient3, "com", "na", "cm",
                "14", LocalDateTime.of(2023, 10, 14, 0, 0));
        Examination examination25 = createExamination(patient3, "com", "nb", "cm",
                "15", LocalDateTime.of(2023, 9, 14, 0, 0));
        Examination examination26 = createExamination(patient3, "com", "nb", "cm",
                "16", LocalDateTime.of(2023, 9, 15, 0, 0));
        Examination examination27 = createExamination(patient3, "com", "nb", "cm",
                "10", LocalDateTime.of(2023, 9, 16, 0, 0));
        Examination examination28 = createExamination(patient3, "com", "nc", "cm",
                "11", LocalDateTime.of(2023, 8, 18, 0, 0));
        Examination examination29 = createExamination(patient3, "com", "nb", "cm",
                "10", LocalDateTime.of(2023, 9, 17, 0, 0));
        Examination examination30 = createExamination(patient3, "com", "nb", "cm",
                "15", LocalDateTime.of(2023, 9, 17, 0, 0));

        Examination examination31 = createExamination(patient4, "com", "na", "cm",
                "10", LocalDateTime.of(2023, 10, 10, 0, 0));
        Examination examination32 = createExamination(patient4, "com", "na", "cm",
                "13", LocalDateTime.of(2023, 10, 12, 0, 0));
        Examination examination33 = createExamination(patient4, "com", "na", "cm",
                "19", LocalDateTime.of(2023, 10, 13, 0, 0));
        Examination examination34 = createExamination(patient4, "com", "na", "cm",
                "14", LocalDateTime.of(2023, 10, 14, 0, 0));
        Examination examination35 = createExamination(patient4, "com", "nb", "cm",
                "15", LocalDateTime.of(2023, 9, 14, 0, 0));
        Examination examination36 = createExamination(patient4, "com", "nb", "cm",
                "16", LocalDateTime.of(2023, 9, 15, 0, 0));
        Examination examination37 = createExamination(patient4, "com", "nb", "cm",
                "10", LocalDateTime.of(2023, 9, 16, 0, 0));
        Examination examination38 = createExamination(patient4, "com", "nc", "cm",
                "11", LocalDateTime.of(2023, 8, 18, 0, 0));
        Examination examination39 = createExamination(patient4, "com", "nb", "cm",
                "10", LocalDateTime.of(2023, 9, 17, 0, 0));
        Examination examination40 = createExamination(patient4, "com", "nb", "cm",
                "15", LocalDateTime.of(2023, 9, 17, 0, 0));

        Examination examination41 = createExamination(patient5, "com", "na", "cm",
                "10", LocalDateTime.of(2023, 10, 10, 0, 0));
        Examination examination42 = createExamination(patient5, "com", "na", "cm",
                "13", LocalDateTime.of(2023, 10, 12, 0, 0));
        Examination examination43 = createExamination(patient5, "com", "na", "cm",
                "19", LocalDateTime.of(2023, 10, 13, 0, 0));
        Examination examination44 = createExamination(patient5, "com", "na", "cm",
                "14", LocalDateTime.of(2023, 10, 14, 0, 0));
        Examination examination45 = createExamination(patient5, "com", "nb", "cm",
                "15", LocalDateTime.of(2023, 9, 14, 0, 0));
        Examination examination46 = createExamination(patient5, "com", "nb", "cm",
                "16", LocalDateTime.of(2023, 9, 15, 0, 0));
        Examination examination47 = createExamination(patient5, "com", "nb", "cm",
                "10", LocalDateTime.of(2023, 9, 16, 0, 0));
        Examination examination48 = createExamination(patient5, "com", "nc", "cm",
                "11", LocalDateTime.of(2023, 8, 18, 0, 0));
        Examination examination49 = createExamination(patient5, "com", "nb", "cm",
                "10", LocalDateTime.of(2023, 9, 17, 0, 0));
        Examination examination50 = createExamination(patient5, "com", "nb", "cm",
                "15", LocalDateTime.of(2023, 9, 17, 0, 0));

        List<Examination> examinations = List.of(examination1, examination2, examination3, examination4, examination5,
                examination6, examination7, examination8, examination9, examination10, examination11, examination12,
                examination13, examination14, examination15, examination16, examination17, examination18, examination19,
                examination20, examination21, examination22, examination23, examination24, examination25, examination26,
                examination27, examination28, examination29, examination30, examination31, examination32, examination33,
                examination34, examination35, examination36, examination37, examination38, examination39, examination40,
                examination41, examination42, examination43, examination44, examination45, examination46, examination47,
                examination48, examination49, examination50);
        examinationRepository.saveAll(examinations);
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
                                          String result, LocalDateTime resultTime) {
        return Examination.builder()
                .patient(patient)
                .commonCode(commonCode)
                .name(name)
                .unitOfMeasure(unitOfMeasure)
                .result(result)
                .resultTime(resultTime)
                .commTime(LocalDateTime.now())
                .resultHeaderName("header")
                .build();
    }

}
