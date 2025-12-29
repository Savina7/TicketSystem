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
    private TicketType ticketType;
    private String qrCode;
    private User user;
    private Float price;
    private TicketStatus status;



}
