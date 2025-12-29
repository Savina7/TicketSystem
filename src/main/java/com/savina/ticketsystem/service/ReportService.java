package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportService {

    private List<Report> reports = new ArrayList<>();

    public Report registerReport(Report report) {
        reports.add(report);
        return report;
    }

    public List<Report> viewReports() {
        return reports;
    }
}
