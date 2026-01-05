package com.savina.ticketsystem.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // ID automatik
    @Column(name = "payment_id", updatable = false)
    private Integer paymentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate; // DB e vendos automatikisht me SYSDATE

    @Column(name = "amount")
    private Float amount;

    @Column(name = "payment_type", length = 50)
    private String paymentType;

    @Column(name = "transaction_code", length = 100)
    private String transactionCode;

    @Column(name = "status", length = 20)
    private String status;

    // Konstruktor pa ID dhe pa paymentDate
    public Payment(User user, Ticket ticket, Float amount, String paymentType, String transactionCode, String status) {
        this.user = user;
        this.ticket = ticket;
        this.amount = amount;
        this.paymentType = paymentType;
        this.transactionCode = transactionCode;
        this.status = status;
    }
}
