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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "STATUS", length = 1)
    private String status;



    // Konstruktor pa ID (ID gjenerohet automatikisht)
    public Bus( Company company) {

        this.company = company;
    }
}

