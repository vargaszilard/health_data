package com.econsult.health.repository;

import com.econsult.health.entity.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository manages basic CRUD operations regarding {@link Examination} entities.
 */
@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {
}
