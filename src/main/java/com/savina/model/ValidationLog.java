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
public class ValidationLog {
    private String validationID;
    private Ticket ticket;
    private Date validationDate;
    private String deviceID;
    private String qrCode;
    private String status;

}
