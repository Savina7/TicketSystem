package com.savina.service;

import com.savina.model.Admin;
import com.savina.model.Company;
import com.savina.model.Report;

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
