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
public class Ticket {
    private String ticketID;
    private Date activationDate;
    private Date expirationDay;
    private String ticketType;
    private String qrCode;
    private User user;
    private Float price;
    private String status;

    public boolean activate(){
        return true;
    }

    public boolean checkValidity(){
        return true;
    }

    public String generateQRCode(){
        return null;
    }
}
