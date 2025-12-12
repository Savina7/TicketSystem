package com.savina.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private String paymentID;
    private User user;        // dmth qe ka nje atribut nga klasa user
    private Ticket ticket;   // dmth qe ka nje atribut nga klasa ticket
    private Date paymentDate;
    private Float amount;
    private String paymentType;
    private String transactionCode;
    private String status;


    public boolean processPayment(){
        return true;
    }

    public String checkStatus(){
        return null;
    }
}
