package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.Company;
import com.savina.ticketsystem.model.Report;
import com.savina.ticketsystem.repository.CompanyRepository;
import com.savina.ticketsystem.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ReportRepository reportRepository;

    // Krijo kompani
    public Company createCompany(String companyName, String companyEmail) {
        // Kontrollo nëse kompania ekziston
        if (companyRepository.existsByCompanyName(companyName)) {
            throw new RuntimeException("Kompania me kete emer egziston");
        }
        Company company = new Company(companyName, companyEmail, "ACTIVE"); // ose status default

        return companyRepository.save(company);
    }


    // Përditëso kompani
    public boolean updateCompany(Integer companyId, String companyName, String companyEmail) {
        Company company = companyRepository.findByCompanyId(companyId)
                .orElseThrow(() -> new RuntimeException("Kjo kompani me kete ID nuk ekziston"));

        company.setCompanyName(companyName);
        company.setCompanyEmail(companyEmail);
        companyRepository.save(company);

        return true;
    }


    // Merr raportet e kompanisë
    public List<Report> viewReports(Long companyID) {
        // logjikën do e implementosh vet
        return null;
    }
}

