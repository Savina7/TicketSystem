package com.savina.ticketsystem.model;
import com.savina.ticketsystem.model.User;


import java.util.Date;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STUDENT")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Student extends User {

    @Column(name = "NR_MATRIKULIMI")
    private String nrMatrikullimi;

    @Column(name = "STUDY_PROGRAM")
    private String studyProgram;

    @Column(name = "YEAR_OF_ENROLLMENT")
    private int yearOfEnrolment;

    @Column(name = "YEAR_OF_GRADUATION")
    private int yearOfGraduation;

    @Column(name = "STATUS")
    private String status;

    public Student() {
        super();
    }

    public Student(String userID, String firstName, String lastName, String email, String phoneNumber,
                   String password, String role, Date registrationDate,
                   String nrMatrikullimi, String studyProgram,
                   int yearOfEnrolment, int yearOfGraduation, String status) {
        super(firstName, lastName, email, phoneNumber, password, role, registrationDate, status);
        this.nrMatrikullimi = nrMatrikullimi;
        this.studyProgram = studyProgram;
        this.yearOfEnrolment = yearOfEnrolment;
        this.yearOfGraduation = yearOfGraduation;
        this.status = status;
    }

    // Getters dhe Setters
    public String getNrMatrikullimi() { return nrMatrikullimi; }
    public void setNrMatrikullimi(String nrMatrikullimi) { this.nrMatrikullimi = nrMatrikullimi; }

    public String getStudyProgram() { return studyProgram; }
    public void setStudyProgram(String studyProgram) { this.studyProgram = studyProgram; }

    public int getYearOfEnrolment() { return yearOfEnrolment; }
    public void setYearOfEnrolment(int yearOfEnrolment) { this.yearOfEnrolment = yearOfEnrolment; }

    public int getYearOfGraduation() { return yearOfGraduation; }
    public void setYearOfGraduation(int yearOfGraduation) { this.yearOfGraduation = yearOfGraduation; }

    @Override
    public String getStatus() { return status; }
    @Override
    public void setStatus(String status) { this.status = status; }
}
