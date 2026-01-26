package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.*;
import com.savina.ticketsystem.repository.TicketRepository;
import com.savina.ticketsystem.repository.UserRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private StudentService studentService;

    @Autowired
    private UserRepository userRepository;


    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket buyTicket(String email, TicketType ticketType) {
        User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("No user exists with this email."));

        if (ticketType == TicketType.STUDENT && !user.getRole().equals("STUDENT")) {
            throw new RuntimeException("Only STUDENT users can purchase this ticket.");
        }
        if (ticketType == TicketType.STUDENT) {
            if (!studentService.verifyStudentStatus(email)) {
                throw new RuntimeException("This ticket can only be purchased by active students.");
            }
        }
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setTicketType(ticketType);
        ticket.setPrice(ticketType.getPrice());
        ticket.setStatus(TicketStatus.BOUGHT);
        ticket.setQrCode(UUID.randomUUID().toString());
        ticket.setActivationDate(null);
        ticket.setExpirationDay(null);

        return ticketRepository.save(ticket);
    }


    public Ticket activateTicket(String qrCode) {
        Ticket ticket = ticketRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getStatus() == TicketStatus.ACTIVE) {
            throw new RuntimeException("Ticket already activated");
        }

        Instant now = Instant.now();
        ticket.setStatus(TicketStatus.ACTIVE);
        ticket.setActivationDate(Date.from(now));

        int minutes = ticket.getTicketType().getDurationInMinutes();
        Instant expiration = now.plus(Duration.ofMinutes(minutes));
        ticket.setExpirationDay(Date.from(expiration));

        return ticketRepository.save(ticket);
    }


    public boolean checkValidity(String qrCode) {
        Ticket ticket = ticketRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getStatus() == TicketStatus.BOUGHT) {
            throw new RuntimeException("This ticket has not been activated yet. Please activate it before use.");
        }

        if (ticket.getStatus() == TicketStatus.EXPIRED) {
            throw new RuntimeException("Ticket is expired");
        }

        if (ticket.getStatus() == TicketStatus.ACTIVE) {
            Instant now = Instant.now();
            Instant activation = ticket.getActivationDate().toInstant();
            Instant expiration = ticket.getExpirationDay().toInstant();

            if (now.isAfter(activation) && now.isBefore(expiration)) {
                System.out.println("Ticket is valid and active");
                return true;
            } else {
                ticket.setStatus(TicketStatus.EXPIRED);
                ticketRepository.save(ticket);
                return false;
            }
        }

        return false;
    }


    public String generateQRCode(Ticket ticket) {
        String qrCode = UUID.randomUUID().toString();
        ticket.setQrCode(qrCode);
        ticketRepository.save(ticket);
        return qrCode;
    }


    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

}
