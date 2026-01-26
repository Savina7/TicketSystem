package com.savina;

import com.savina.ticketsystem.model.*;
import com.savina.ticketsystem.repository.ReportRepository;
import com.savina.ticketsystem.repository.TicketRepository;
import com.savina.ticketsystem.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterReport_Sales() {
        Company company = new Company();
        User user = new User();
        Report reportToReturn = new Report();

        when(reportRepository.save(any(Report.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(ticketRepository.findByActivationDateBetween(any(Date.class), any(Date.class)))
                .thenReturn(Arrays.asList(
                        createTicket(10.0f),
                        createTicket(20.0f)
                ));

        Report report = reportService.registerReport(ReportType.SALES, ReportPeriod.DAILY, company, user);

        assertNotNull(report);
        assertEquals(ReportType.SALES, report.getReportType());
        assertEquals(company, report.getCompany());
        assertEquals(user, report.getUser());
        assertNull(report.getTicket()); // për SALES, ticket mbetet null

        verify(reportRepository, times(1)).save(report);
        verify(ticketRepository, times(1)).findByActivationDateBetween(any(Date.class), any(Date.class));
    }

    @Test
    public void testRegisterReport_TopTicket() {
        Company company = new Company();
        User user = new User();

        Ticket topTicket = createTicket(15.0f);
        Ticket otherTicket = createTicket(10.0f);


        when(ticketRepository.findByActivationDateBetween(any(Date.class), any(Date.class)))
                .thenReturn(Arrays.asList(otherTicket, topTicket, topTicket));

        when(reportRepository.save(any(Report.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Report report = reportService.registerReport(ReportType.TOP_TICKET, ReportPeriod.DAILY, company, user);

        assertNotNull(report);
        assertEquals(ReportType.TOP_TICKET, report.getReportType());
        assertEquals(topTicket, report.getTicket());

        verify(reportRepository, times(1)).save(report);
        verify(ticketRepository, times(1)).findByActivationDateBetween(any(Date.class), any(Date.class));
    }



    @Test
    public void testViewReports() {
        Report report1 = new Report();
        Report report2 = new Report();

        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        List<Report> reports = reportService.viewReports();

        assertEquals(2, reports.size());
        assertEquals(report1, reports.get(0));
        assertEquals(report2, reports.get(1));
    }


    private Ticket createTicket(float price) {
        Ticket ticket = new Ticket();
        ticket.setPrice(price);
        ticket.setActivationDate(new Date());
        return ticket;
    }
}