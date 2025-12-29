package com.savina.app;

import com.savina.ticketsystem.model.TicketType;
import com.savina.ticketsystem.service.TicketService;
import com.savina.ticketsystem.service.UserService;

public class App {
    public static void main(String[] args) {

        // 1️⃣ Regjistro admin
        UserService userService = new UserService();
        userService.registerAdmin("Arber", "Hoxha",
                "arber.hoxha@abc.com", "09988", "3434534");

        // 2️⃣ Bli një biletë për këtë admin duke përdorur email-in
        TicketService ticketService = new TicketService();
        ticketService.buyTicket("arber.hoxha@abc.com", TicketType.DAILY);


    }
}
