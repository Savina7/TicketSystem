package com.savina.ticketsystem.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADMIN", schema = "SOFT")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Admin extends User {

    @Column(name = "COMPANY_ID")
    private Integer companyID; // Ndryshuar në Integer sipas SQL

    @Column(name = "ADMIN_ID_NUMBER")
    private String adminIdNumber; // Kjo ishte fusha që mungonte

    @Column(name = "STATUS")
    private String adminStatus; // Kjo i korrespondon CHAR(1) ne SQL


    public Admin(String firstName, String lastName, String email, String phoneNumber,
                 String password, String role, Date registrationDate, String status,
                 Integer companyID, String adminIdNumber) {
        super(firstName, lastName, email, phoneNumber, password, role, registrationDate, status);
        this.companyID = companyID;
        this.adminIdNumber = adminIdNumber;
        this.adminStatus = "1"; // Default aktiv
    }
}