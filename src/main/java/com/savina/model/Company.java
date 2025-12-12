package com.savina.model;
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

    public Company create(){
        return null;
    }

    public boolean update(){
        return true;
    }

    public List<Report> viewReports(){
        return null;
    }
}
