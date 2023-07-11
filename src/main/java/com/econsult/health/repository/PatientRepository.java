package com.econsult.health.repository;

import com.econsult.health.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository manages basic CRUD operations regarding {@link Patient} entities.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
