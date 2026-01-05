package com.savina.ticketsystem.repository;

import com.savina.ticketsystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    Optional<Ticket> findByQrCode(String qrCode);
}
//Repository-t veprojnë si komanda SQL, por në nivel objektesh.