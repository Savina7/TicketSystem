package com.savina.ticketsystem.repository;

import com.savina.ticketsystem.model.Notification;

import com.savina.ticketsystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface NotificationRepository extends JpaRepository<Notification, String> {

    Optional<Notification> findById(Long notificationId);
}
