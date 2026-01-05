package com.savina.ticketsystem.model;


import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // ID automatik
    @Column(name = "company_id", updatable = false)
    private Integer companyId;

    @Column(name = "company_name", length = 100)
    private String companyName;

    @Column(name = "company_email", length = 100)
    private String companyEmail;

    @Column(name = "status", length = 20)
    private String status;

    // Një kompani ka shumë autobusë
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bus> buses;

    // Konstruktor pa ID (ID gjenerohet automatikisht)
    public Company(String companyName, String companyEmail, String status) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.status = status;
    }
}
