package com.savina.ticketsystem.repository;
import com.savina.ticketsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // metodat tuaja të veçanta këtu
}
