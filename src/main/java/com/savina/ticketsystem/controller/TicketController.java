package com.savina.ticketsystem.controller;

import com.savina.ticketsystem.model.Ticket;
import com.savina.ticketsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Kjo shfaq QR Kodin në PC
    @GetMapping(value = "/generate/{qrCode}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generate(@PathVariable String qrCode) throws Exception {
        // IP-ja jote që gjetëm te CMD
        String url = "http://192.168.137.1:9090/api/tickets/activate/" + qrCode;
        return ticketService.generateQRCodeImage(url);
    }

    // Kjo thirret nga telefoni
    @GetMapping("/activate/{qrCode}")
    public String activate(@PathVariable String qrCode) {
        try {
            ticketService.activateTicket(qrCode);
            return "<html><body style='text-align:center;'><h1>✅ Bileta u aktivizua!</h1></body></html>";
        } catch (Exception e) {
            return "<html><body style='text-align:center;'><h1>❌ Gabim: " + e.getMessage() + "</h1></body></html>";
        }
    }
}

