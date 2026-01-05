package com.savina.ticketsystem.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERI")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userID;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ROLE")
    private String role;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGISTRATION_DATE", updatable = false) // mos e lejo ndryshimin pas regjistrimit
    private Date registrationDate = new Date();

    @Column(name = "STATUS")
    private String status;

    // Lidhja me Ticket
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    // Konstruktor pa userID (do të gjenerohet automatikisht)
    public User(String firstName, String lastName, String email, String phoneNumber,
                String password, String role, Date registrationDate, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.registrationDate = registrationDate;
        this.status = status;
    }
}
