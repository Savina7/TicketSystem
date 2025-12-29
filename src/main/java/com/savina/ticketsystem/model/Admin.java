package com.savina.ticketsystem.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADMIN")
@PrimaryKeyJoinColumn(name = "USER_ID") // lidh Admin me User në Inheritance
public class Admin extends User {

    @Column(name = "COMPANY_ID")
    private String companyID;

    // Konstruktor pa userID (gjenerohet automatikisht)
    public Admin(String firstName, String lastName, String email, String phoneNumber,
                 String password, String role, Date registrationDate, String status,
                 String companyID) {
        super(firstName, lastName, email, phoneNumber, password, role, registrationDate, status);
        this.companyID = companyID;
    }
}
