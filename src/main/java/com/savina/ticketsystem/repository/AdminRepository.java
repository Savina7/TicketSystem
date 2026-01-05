package com.savina.ticketsystem.repository;
import com.savina.ticketsystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> { }

