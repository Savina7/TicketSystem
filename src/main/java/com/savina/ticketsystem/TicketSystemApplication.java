package com.savina.ticketsystem;

import com.savina.ticketsystem.model.Ticket;
import com.savina.ticketsystem.model.TicketType;
import com.savina.ticketsystem.service.TicketService;
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
    CommandLineRunner setupData(UserService userService, TicketService ticketService) {
        return args -> {


            // 2️⃣ Test Login
            String role = userService.login("saavina.shimi@fti.com", "user123");
            System.out.println("--- Savina blen bileten ditore ---");

            // Blen bileten ditore
            Ticket dailyTicket = ticketService.buyTicket(
                    "savina.shimi@fti.com",  // email i user
                    TicketType.DAILY          // supozim TicketType enum ekziston
            );

            Ticket activatedTicket = ticketService.activateTicket(dailyTicket.getQrCode());
            System.out.println("Ticket u aktivizua me sukses. Skadon ne: " + activatedTicket.getExpirationDay());

            boolean isValid = ticketService.checkValidity(dailyTicket.getQrCode());
            System.out.println("Ticket valid: " + isValid);
            System.out.println("Ticket u krijua me QR Code: " + dailyTicket.getQrCode());
        };
    }

}
