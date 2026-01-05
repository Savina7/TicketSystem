package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.Notification;
import com.savina.ticketsystem.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Dërgon dhe ruan notification në DB
    public Notification sendNotification(Notification notification) {
        if (notification.getUser() == null) {
            throw new IllegalArgumentException("User ID duhet të jetë i plotësuar");
        }
        if (notification.getMessage() == null || notification.getMessage().isEmpty()) {
            throw new IllegalArgumentException("Message nuk mund të jetë bosh");
        }

        notification.setCreatedAt(new Date());
        notification.setIsRead(false);

        return notificationRepository.save(notification);
    }

    // Marko notification si lexuar
    public boolean readNotification(Long notificationId) {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        if (optionalNotification.isPresent()) {
            Notification notification = optionalNotification.get();
            notification.setIsRead(true);
            notificationRepository.save(notification);
            return true;
        }
        return false;
    }

    // Schedule notification
    public boolean scheduleNotification(Long notificationId, Date scheduledTime) {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);

        if (optionalNotification.isPresent()) {
            Notification notification = optionalNotification.get();
            notification.setScheduledTime(scheduledTime);
            notification.setIsRead(false); // Nëse do ta bëjmë “not read” përsëri
            notificationRepository.save(notification);
            return true;
        }

        return false; // Nëse notifikimi nuk ekziston
    }



}
