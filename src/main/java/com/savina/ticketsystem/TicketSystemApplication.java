package com.savina.ticketsystem;

import com.savina.ticketsystem.model.*;
import com.savina.ticketsystem.service.CompanyService;
import com.savina.ticketsystem.service.ReportService;
import com.savina.ticketsystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner testReport(ReportService reportService) {
        return args -> {

            ReportType type = ReportType.SALES;
            ReportPeriod period = ReportPeriod.DAILY;

            // 3️⃣ Krijo dhe ruaj raportin
            Report savedReport = reportService.registerReport(type, period, company, user);

            // 4️⃣ Printo për të kontrolluar
            System.out.println("Raporti u ruajt me sukses!");
            System.out.println("ID: " + savedReport.getReportID());
            System.out.println("Tipi: " + savedReport.getReportType());
            System.out.println("Periudha: " + period);
            System.out.println("Kompania: " + company.getCompanyName());
            System.out.println("User: " + user.getFirstName() + " " + user.getLastName());
        };
    }


}
