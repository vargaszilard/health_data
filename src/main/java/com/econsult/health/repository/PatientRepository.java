package com.econsult.health.repository;

import com.econsult.health.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * This repository manages basic CRUD operations regarding {@link Patient} entities.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Returns the patient with the given BirthDate and SSN
     * @param birthDate The birthdate of the patient
     * @param ssn the SSN of the patient
     * @return an Optional that contains the Patient or en empty Optional
     */
    Optional<Patient> findByBirthDateAndSsn(LocalDate birthDate, String ssn);
}
