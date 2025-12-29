package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.Admin;
import com.savina.ticketsystem.model.Company;
import com.savina.ticketsystem.model.Report;

public class AdminService {

    public boolean manageUser(Admin admin) {
        // logjikë për menaxhimin e user-eve
        return true;
    }

    public Company addCompany(Admin admin, Company company) {
        // shton kompani
        return company;
    }

    public Report generateReport(Admin admin) {
        // gjeneron raport
        return new Report();
    }
}
