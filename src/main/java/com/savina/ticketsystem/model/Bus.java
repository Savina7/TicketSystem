package com.savina.ticketsystem.model;




import lombok.*;
import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // gjeneron automatikisht ID
    private Integer busId;

    @Column(name = "bus_number", length = 20)
    private String busNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    // Konstruktor pa ID (ID gjenerohet automatikisht)
    public Bus(String busNumber, Company company) {
        this.busNumber = busNumber;
        this.company = company;
    }
}

