package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.Payment;

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
