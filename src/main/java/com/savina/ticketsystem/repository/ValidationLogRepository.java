package com.savina.ticketsystem.repository;

import com.savina.ticketsystem.model.ValidationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationLogRepository extends JpaRepository<ValidationLog, Integer> {

}
