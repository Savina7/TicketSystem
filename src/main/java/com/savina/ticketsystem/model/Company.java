package com.savina.ticketsystem.model;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private String companyID;
    private String companyName;
    private String companyEmail;
    private String status;


}
