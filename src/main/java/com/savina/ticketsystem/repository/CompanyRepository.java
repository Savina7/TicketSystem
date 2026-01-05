package com.savina.ticketsystem.repository;


import com.savina.ticketsystem.model.Company;
import com.savina.ticketsystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    boolean existsByCompanyName(String companyName);
    Optional<Company> findByCompanyId(Integer companyId);
}