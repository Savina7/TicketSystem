package com.savina.ticketsystem;

import com.savina.ticketsystem.model.Ticket;
import com.savina.ticketsystem.service.TicketService;
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
    CommandLineRunner testDatabase(TicketService ticketService) {
        return args -> {
            System.out.println("Testing database connection...");

            // provon të marrë të gjithë tiketat (supozohet që TicketService ka një metodë findAll)
            ticketService.findAll().forEach(ticket ->
                    System.out.println(ticket)
            );

            System.out.println("Database connection successful!");
        };
    }
}
