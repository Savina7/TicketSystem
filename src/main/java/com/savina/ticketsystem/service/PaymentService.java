package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.Payment;
import com.savina.ticketsystem.model.Ticket;
import com.savina.ticketsystem.repository.PaymentRepository;
import com.savina.ticketsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TicketRepository ticketRepository;

    // Proceson dhe ruan pagesën në databazë
    public Payment processPayment(Long ticketId, Payment payment) {
        // 1️⃣ Vendos datën dhe transaction code
        payment.setPaymentDate(new Date());
        payment.setTransactionCode(UUID.randomUUID().toString());
        payment.setStatus("C"); // COMPLETED

        // 2️⃣ Vendos paymentType gjithmonë "CARD"
        payment.setPaymentType("CARD");

        // 3️⃣ Merr çmimin e ticket dhe e shton tek amount
        Optional<Ticket> ticketOpt = ticketRepository.findById(String.valueOf(ticketId));

        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();

            float currentAmount = payment.getAmount() != null ? payment.getAmount() : (float) 0.0;
            payment.setAmount(currentAmount + ticket.getPrice());
        } else {
            if (payment.getAmount() == null) {
                payment.setAmount(0.0f);
            }
        }

        // 4️⃣ Ruaj payment-in në bazë
        return paymentRepository.save(payment);
    }


    // Kontrollon statusin e pagesës
    public String checkStatus(Long paymentId) {
        Optional<Payment> optionalPayment = paymentRepository.findById(String.valueOf(paymentId));

        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            // kthe statusin si string
            return String.valueOf(payment.getStatus());
        } else {
            return "NOT FOUND";
        }
    }

}
