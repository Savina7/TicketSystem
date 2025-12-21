package com.savina.service;

import com.savina.model.Payment;
import com.savina.model.Ticket;
import com.savina.model.User;

import java.util.Date;
import java.util.UUID;

public class PaymentService {

    public boolean processPayment(Payment payment) {
        payment.setPaymentDate(new Date());
        payment.setTransactionCode(UUID.randomUUID().toString());
        payment.setStatus("COMPLETED");
        return true;
    }

    public String checkStatus(Payment payment) {
        return payment.getStatus();
    }
}
