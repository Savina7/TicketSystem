package com.savina.ticketsystem.repository;
import com.savina.ticketsystem.model.Student;
import com.savina.ticketsystem.model.StudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
    Optional<Student> findByEmail(String email);
}