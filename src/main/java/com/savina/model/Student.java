package com.savina.model;

import java.util.Date;

public class Student  extends  User{
    private String nrMatrikullimi;
    private String studyProgram;
    private int yearOfEnrolment;
    private int yearOfGraduation;
    private String status;


    public Student(String userID, String firstName, String lastName, String email, String phoneNumber, String password, String role, Date registrationDate ,String nrMatrikullimi, String studyProgram,
                   int yearOfEnrolment, int yearOfGraduation, String status) {
        super(userID,firstName, lastName, email, phoneNumber, password, role, registrationDate,status );
        this.nrMatrikullimi = nrMatrikullimi;
        this.studyProgram = studyProgram;
        this.yearOfEnrolment = yearOfEnrolment;
        this.yearOfGraduation = yearOfGraduation;

    }

    public String getNrMatrikullimi() {
        return nrMatrikullimi;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public int getYearOfEnrolment() {
        return yearOfEnrolment;
    }

    public int getYearOfGraduation() {
        return yearOfGraduation;
    }

    public String getStatus() {
        return status;
    }
    public void setNrMatrikullimi(String nrMatrikullimi) {
        this.nrMatrikullimi = nrMatrikullimi;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    public void setYearOfEnrolment(int yearOfEnrolment) {
        this.yearOfEnrolment = yearOfEnrolment;
    }

    public void setYearOfGraduation(int yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public  boolean verifyStudentStatus(){
        return true;
    }
    public boolean updateStatus(){
        return true;
    }

}
