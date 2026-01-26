package com.savina;

import com.savina.ticketsystem.model.Payment;
import com.savina.ticketsystem.model.Ticket;
import com.savina.ticketsystem.repository.PaymentRepository;
import com.savina.ticketsystem.repository.TicketRepository;
import com.savina.ticketsystem.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessPayment_TicketExists() {
        Ticket ticket = new Ticket();
        ticket.setPrice(50.0f);

        Payment payment = new Payment();
        payment.setAmount(20.0f);

        when(ticketRepository.findById(anyString())).thenReturn(Optional.of(ticket));
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment result = paymentService.processPayment(1L, payment);

        assertNotNull(result.getPaymentDate(), "Payment date duhet të ekzistojë");
        assertNotNull(result.getTransactionCode(), "Transaction code duhet të ekzistojë");
        assertEquals("C", result.getStatus());
        assertEquals("CARD", result.getPaymentType());
        assertEquals(70.0f, result.getAmount()); // 20 + 50
    }

    @Test
    public void testProcessPayment_TicketNotFound_AmountNull() {
        Payment payment = new Payment(); // amount null

        when(ticketRepository.findById(anyString())).thenReturn(Optional.empty());
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment result = paymentService.processPayment(999L, payment);

        assertEquals(0.0f, result.getAmount());
        assertEquals("C", result.getStatus());
        assertEquals("CARD", result.getPaymentType());
    }

    @Test
    public void testCheckStatus_PaymentExists() {
        Payment payment = new Payment();
        payment.setStatus("C");

        when(paymentRepository.findById(anyString())).thenReturn(Optional.of(payment));

        String status = paymentService.checkStatus(1L);
        assertEquals("C", status);
    }

    @Test
    public void testCheckStatus_PaymentNotFound() {
        when(paymentRepository.findById(anyString())).thenReturn(Optional.empty());

        String status = paymentService.checkStatus(999L);
        assertEquals("NOT FOUND", status);
    }
}