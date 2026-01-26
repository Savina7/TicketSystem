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


    public Payment processPayment(Long ticketId, Payment payment) {

        payment.setPaymentDate(new Date());
        payment.setTransactionCode(UUID.randomUUID().toString());
        payment.setStatus("C"); // COMPLETED

        payment.setPaymentType("CARD");

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

        return paymentRepository.save(payment);
    }


    public String checkStatus(Long paymentId) {
        Optional<Payment> optionalPayment = paymentRepository.findById(String.valueOf(paymentId));

        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();

            return String.valueOf(payment.getStatus());
        } else {
            return "NOT FOUND";
        }
    }

}
