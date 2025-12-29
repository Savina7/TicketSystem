package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.Ticket;
import com.savina.ticketsystem.model.TicketStatus;
import com.savina.ticketsystem.model.TicketType;
import com.savina.ticketsystem.model.User;
import com.savina.ticketsystem.repository.TicketRepository;
import com.savina.ticketsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    // Merr të gjitha biletat
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    // Ble një ticket
    public Ticket buyTicket(String email, TicketType ticketType) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User nuk ekziston me këtë email"));

        Ticket ticket = new Ticket();
        ticket.setUser(user); // lidhet automatikisht me user
        ticket.setTicketType(ticketType);
        ticket.setPrice(ticketType.getPrice());
        ticket.setStatus(TicketStatus.BOUGHT);
        ticket.setQrCode(UUID.randomUUID().toString());
        ticket.setActivationDate(null); // nuk aktivizohet menjëherë
        ticket.setExpirationDay(null); // vendos më vonë nëse duhet

        return ticketRepository.save(ticket); // insert automatik në DB
    }

    public boolean activateTicket(Ticket ticket) {
        ticket.setStatus(TicketStatus.ACTIVE);
        ticket.setActivationDate(new Date());
        ticketRepository.save(ticket);
        return true;
    }

    public boolean checkValidity(Ticket ticket) {
        Date today = new Date();
        return ticket.getExpirationDay() != null && ticket.getExpirationDay().after(today);
    }

    public String generateQRCode(Ticket ticket) {
        String qrCode = UUID.randomUUID().toString();
        ticket.setQrCode(qrCode);
        ticketRepository.save(ticket);
        return qrCode;
    }
}
