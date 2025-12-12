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
public class Report {
    private String reportID;
    private String reportType;
    private Date reportTime;
    private Company company;
    private User user;
    private Ticket ticket;
    private Bus bus;


    public Report registerReport(){
        return null;
    }

    public List<Report> viewReports(){
        return null;
    }
}
