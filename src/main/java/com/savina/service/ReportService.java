package com.savina.service;

import com.savina.model.Report;

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
