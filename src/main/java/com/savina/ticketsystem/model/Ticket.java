package com.savina.ticketsystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TICKET")
public class Ticket {

    @Id
    @Column(name = "TICKET_ID")
    private String ticketID;

    @Column(name = "ACTIVATION_DATE")
    private Date activationDate;

    @Column(name = "EXPIRATION_DAY")
    private Date expirationDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "TICKET_TYPE")
    private TicketType ticketType;

    @Column(name = "QR_CODE")
    private String qrCode;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "PRICE")
    private Float price;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private TicketStatus status;
}
