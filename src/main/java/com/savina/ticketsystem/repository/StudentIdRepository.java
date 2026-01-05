package com.savina.ticketsystem.repository;

import com.savina.ticketsystem.model.StudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentIdRepository extends JpaRepository<StudentId, String> {
    Optional<StudentId> findByStudentNameAndStudentSurnameAndEmail(String name, String surname, String email);
}