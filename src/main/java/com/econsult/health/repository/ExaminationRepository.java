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

    /**
     * Gets the results of a Patient to a commCode
     * @param patientId Id of the patient
     * @param commCode Code of the comm
     * @return List of Strings contains the results
     */
    @Query("SELECT ex.result FROM Examination ex WHERE ex.patient.id = :patientId AND ex.commonCode = :commCode")
    List<String> findResultsByPatientIdAndCommCode(long patientId, String commCode);

    /**
     * Get the Names, ResultTimes and Results of Examinations to a given PatientId in increasing order to ResultTime
     * @param patientId Id of the Patient who owns the examinations
     * @return List of Object array witch stores the given fields
     */
    @Query("SELECT ex.name, ex.resultTime, ex.result FROM Examination ex WHERE ex.patient.id = :patientId ORDER BY ex.resultTime ASC")
    List<Object[]> getTendencies(long patientId);

}
