package com.savina.model;

import java.util.Date;

public class Admin extends User{
    private String companyID;

    public Admin(String companyID, String userID, String firstName, String lastName, String email, String phoneNumber, String password, String role, Date registrationDate , String status){
        super( userID, firstName, lastName, email, phoneNumber, password, role, registrationDate , status);
        this.companyID=companyID;
   }
    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public boolean manageUser(){
        return true;
    }
    public Company addCompany(){
        return null;
    }
    public  Report generateReport(){
        return null;
    }
}
