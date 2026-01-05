package com.savina.ticketsystem.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // ID automatik
    @Column(name = "notif_id", updatable = false)
    private Integer notifID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "message", length = 255)
    private String message;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; // DB e vendos automatikisht me SYSDATE

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "scheduled_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledTime;

    // Konstruktor pa ID dhe pa createdAt
    public Notification(User user, Ticket ticket, String type, String message, Boolean isRead, Date scheduledTime) {
        this.user = user;
        this.ticket = ticket;
        this.type = type;
        this.message = message;
        this.isRead = isRead;
        this.scheduledTime = scheduledTime;
    }
}
