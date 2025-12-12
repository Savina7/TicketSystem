package com.savina.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userID;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    private String password;
    protected String role;
    protected Date registrationDate;
    protected String status;


    public void register(){}

    public boolean logIn(){
        return true;
    }

    public List<Ticket> searchTickets(){
        return null;
    }

    public Ticket buyTicket(){
        return null;
    }
}
