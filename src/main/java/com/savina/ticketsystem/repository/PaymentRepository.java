package com.savina.ticketsystem.repository;

import com.savina.ticketsystem.model.Notification;
import com.savina.ticketsystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface PaymentRepository extends JpaRepository<Payment, String> {


}