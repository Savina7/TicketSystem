package com.savina.ticketsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.savina.ticketsystem.model.AdminId;
import java.util.Optional;

public interface AdminIdRepository extends JpaRepository<AdminId, String> {
    Optional<AdminId> findByAdminNameAndAdminSurnameAndEmail(String name, String surname, String email);
}