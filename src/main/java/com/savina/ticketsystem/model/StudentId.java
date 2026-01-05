package com.savina.ticketsystem.model;


import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STUDENT_ID", schema = "SOFT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Sepse përdor Sequence (ISEQ$$_...)
    @Column(name = "STUDENT_ID")
    private Integer studentId;

    @Column(name = "STUDENT_ID_NUMBER", length = 50, unique = true)
    private String studentIdNumber;

    @Column(name = "STUDENT_NAME", length = 100)
    private String studentName;

    @Column(name = "STUDENT_SURNAME", length = 100)
    private String studentSurname;

    @Column(name = "STUDY_PROGRAM", length = 100)
    private String studyProgram;

    @Column(name = "YEAR_OF_ENROLLMENT")
    private Integer yearOfEnrollment;

    @Column(name = "YEAR_OF_GRADUATION")
    private Integer yearOfGraduation;

    @Column(name = "EMAIL", length = 50)
    private String email;
}