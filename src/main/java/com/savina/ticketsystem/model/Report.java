package com.savina.ticketsystem.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // ID automatik
    @Column(name = "report_id", updatable = false)
    private Integer  reportID;

    @Column(name = "report_type", length = 50)
    private String reportType;

    @Column(name = "report_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportTime; // DB e vendos automatikisht me SYSDATE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    private Bus bus;

    // Konstruktor pa ID dhe pa reportTime
    public Report(String reportType, Company company, User user, Ticket ticket, Bus bus) {
        this.reportType = reportType;
        this.company = company;
        this.user = user;
        this.ticket = ticket;
        this.bus = bus;
    }
}
