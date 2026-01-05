package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.*;
import com.savina.ticketsystem.repository.ReportRepository;
import com.savina.ticketsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private TicketRepository ticketRepository;

    /**
     * Krijon raportin bazuar në reportType dhe reportPeriod të dhënë
     */
    public Report registerReport(ReportType type, ReportPeriod period, Company company, User user) {
        // Krijo objektin Report
        Report report = new Report();
        report.setReportType(type);
        report.setCompany(company);
        report.setUser(user);

        // ticket dhe bus mbeten null
        report.setTicket(null);
        report.setBus(null);

        // Llogarit raportin sipas tipit
        switch (type) {
            case SALES:
                calculateSales(report, period);
                break;
            case TOP_TICKET:
                calculateTopTicket(report, period);
                break;
            default:
                throw new IllegalArgumentException("Ky raport nuk mbështetet: " + type);
        }

        // Ruaj raportin
        return reportRepository.save(report);
    }


    /**
     * Raporti i shitjeve totale (gjithëpërfshirës)
     */
    private void calculateSales(Report report, ReportPeriod period) {
        Date startDate = getStartDate(period);
        Date endDate = new Date();

        // Merr të gjitha biletat e aktivizuara në periudhën e zgjedhur
        List<Ticket> tickets = ticketRepository.findByActivationDateBetween(startDate, endDate);

        // Llogarit totalin e shitjeve
        double totalSales = tickets.stream()
                .mapToDouble(Ticket::getPrice)
                .sum();

        System.out.println("Total Sales: " + totalSales);

        // Opsionale: ruaje totalin në Report nëse ke fushë për këtë
        // report.setTotalSales(totalSales);
    }

    /**
     * Raporti i biletës më të shitur (gjithëpërfshirës)
     */
    private void calculateTopTicket(Report report, ReportPeriod period) {
        Date startDate = getStartDate(period);
        Date endDate = new Date();

        // Merr të gjitha biletat e aktivizuara në periudhë
        List<Ticket> tickets = ticketRepository.findByActivationDateBetween(startDate, endDate);

        // Grupi sipas biletës dhe numëron sa herë është shitur secila
        Map<Ticket, Long> ticketCounts = tickets.stream()
                .collect(Collectors.groupingBy(ticket -> ticket, Collectors.counting()));

        // Gjej biletën më të shitur
        Ticket topTicket = ticketCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        report.setTicket(topTicket);
    }

    /**
     * Kthen datën e fillimit të periudhës së raportit
     */
    private Date getStartDate(ReportPeriod period) {
        Calendar cal = Calendar.getInstance();

        switch (period) {
            case HOURLY:
                cal.add(Calendar.HOUR_OF_DAY, -1);
                break;
            case DAILY:
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                break;
            case WEEKLY:
                cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                break;
            case MONTHLY:
                cal.set(Calendar.DAY_OF_MONTH, 1);
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                break;
        }

        return cal.getTime();
    }

    /**
     * Shfaq të gjitha raportet
     */
    public List<Report> viewReports() {
        return reportRepository.findAll();
    }
}
