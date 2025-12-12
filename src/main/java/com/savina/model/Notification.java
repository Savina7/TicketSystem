package com.savina.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private String notifID;
    private User user;
    private Ticket ticket;
    private String type;
    private String message;
    private Date createdAt;
    private Boolean isRead;
    private Date scheduledTime;


    public Notification sendNotification(){
        return null;
    }

    public boolean read(){
        return true;
    }

    public boolean scheduleNotification(){
        return true;
    }
}
