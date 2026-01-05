package com.savina.ticketsystem.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "REPORT")  // Caps lock për Oracle
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REPORT_ID", updatable = false)
    private Integer reportID;



    @Enumerated(EnumType.STRING)
    @Column(name = "REPORT_TYPE", length = 50)
    private ReportType reportType;

    @Column(name = "REPORT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportTime = new Date(); // Vendos SYSDATE automatikisht

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_ID")
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUS_ID")
    private Bus bus;

    // 🔹 Opsionale: mund të shtosh fushën për totalSales
    // @Column(name = "TOTAL_SALES")
    // private Float totalSales;

    // Konstruktor pa ID dhe reportTime
    public Report(ReportType reportType, Company company, User user, Ticket ticket, Bus bus) {
        this.reportType = reportType;
        this.company = company;
        this.user = user;
        this.ticket = ticket;
        this.bus = bus;
    }
}
