package com.savina.ticketsystem.model;

import lombok.*;
import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTIVATION_DATE")
    private Date activationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;


    @Column(name = "QR_CODE", length = 1000)
    private String qrCode;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "TICKET_TYPE")
    private TicketType ticketType;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private TicketStatus status;

    @Column(name = "PRICE")
    private Float price;

    @Column(name = "EXPIRATION_DAY")
    private Date expirationDay;

    // Konstruktor pa ticketId (ID do të gjenerohet nga DB)
    public Ticket(Date activationDate, Date expirationDate, TicketType ticketType, String qrCode,
                  User user, TicketStatus status, Float price, Date expirationDay) {
        this.activationDate = activationDate;
        this.expirationDate = expirationDate;
        this.ticketType = ticketType;
        this.qrCode = qrCode;
        this.user = user;
        this.status = status;
        this.price = price;
        this.expirationDay = expirationDay;
    }
}
