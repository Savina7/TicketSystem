package com.savina.ticketsystem.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "validation_log")
public class ValidationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // ID automatik
    @Column(name = "validation_id", updatable = false)
    private Integer validationID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id") // lidhja me tabelën Ticket
    private Ticket ticket;

    @Column(name = "validation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validationDate; // SYSDATE e vendos databaza

    @Column(name = "device_id", length = 50)
    private String deviceID; // mund të lidhet me Device në të ardhmen

    @Column(name = "qr_code", length = 100)
    private String qrCode;

    @Column(name = "status", length = 20)
    private String status;

    // Konstruktor pa ID dhe pa validationDate
    public ValidationLog(Ticket ticket, String deviceID, String qrCode, String status) {
        this.ticket = ticket;
        this.deviceID = deviceID;
        this.qrCode = qrCode;
        this.status = status;
    }
}
