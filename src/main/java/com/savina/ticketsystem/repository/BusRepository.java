package com.savina.ticketsystem.repository;

import com.savina.ticketsystem.model.AdminId;
import com.savina.ticketsystem.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Integer> {

}