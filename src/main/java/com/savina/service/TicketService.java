package com.savina.service;

import com.savina.model.Ticket;
import com.savina.model.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TicketService {

    public List<Ticket> searchTickets() {
        // kërkim biletash (placeholder)
        return null;
    }

    public Ticket buyTicket(User user, Ticket ticket) {
        ticket.setUser(user);
        ticket.setStatus("BOUGHT");
        ticket.setActivationDate(new Date());
        generateQRCode(ticket);
        return ticket;
    }

    public boolean activateTicket(Ticket ticket) {
        ticket.setStatus("ACTIVE");
        ticket.setActivationDate(new Date());
        return true;
    }

    public boolean checkValidity(Ticket ticket) {
        Date today = new Date();
        return ticket.getExpirationDay().after(today);
    }

    public String generateQRCode(Ticket ticket) {
        String qrCode = UUID.randomUUID().toString();
        ticket.setQrCode(qrCode);
        return qrCode;
    }
}
