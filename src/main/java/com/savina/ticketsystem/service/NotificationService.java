package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.Notification;

import java.util.Date;

public class NotificationService {

    public Notification sendNotification(Notification notification) {
        // logjikë për të dërguar notification
        notification.setCreatedAt(new Date());
        notification.setIsRead(false);
        return notification;
    }

    public boolean readNotification(Notification notification) {
        notification.setIsRead(true);
        return true;
    }

    public boolean scheduleNotification(Notification notification, Date scheduledTime) {
        notification.setScheduledTime(scheduledTime);
        return true;
    }
}
