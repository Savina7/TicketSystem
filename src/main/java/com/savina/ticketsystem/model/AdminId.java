package com.savina.ticketsystem.model;


import lombok.*;
import javax.persistence.*;


@Entity
@Table(name = "ADMIN_ID", schema = "SOFT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminId {

    @Id
    @Column(name = "ADMIN_ID_NUMBER", length = 50)
    private String adminIdNumber;

    @Column(name = "ADMIN_NAME", length = 100)
    private String adminName;

    @Column(name = "ADMIN_SURNAME", length = 100)
    private String adminSurname;

    @Column(name = "COMPANY_ID")
    private Integer companyId;

    @Column(name = "EMAIL", length = 50)
    private String email;
}