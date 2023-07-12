package com.econsult.health.repository;

import com.econsult.health.entity.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This repository manages basic CRUD operations regarding {@link Examination} entities.
 */
@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    /**
     * Gets the results of a Patient
     * @param patientId Id of the patient
     * @return List of Strings contains the results
     */
    @Query("SELECT ex.result FROM Examination ex WHERE ex.patient.id = :patientId")
    List<String> findResultsByPatientId(Long patientId);
}
