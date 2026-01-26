package com.savina;

import com.savina.ticketsystem.model.*;
import com.savina.ticketsystem.repository.TicketRepository;
import com.savina.ticketsystem.repository.UserRepository;
import com.savina.ticketsystem.service.StudentService;
import com.savina.ticketsystem.service.TicketService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.savina.ticketsystem.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import java.util.Date;
import java.util.UUID;



class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private TicketService ticketService;

    private User user;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setEmail("student@example.com");
        user.setRole("STUDENT");

        ticket = new Ticket();
        ticket.setUser(user);
        ticket.setTicketType(TicketType.STUDENT);
        ticket.setStatus(TicketStatus.BOUGHT);
        ticket.setQrCode(UUID.randomUUID().toString());
    }


    @Test
    void testBuyTicket_Success() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(studentService.verifyStudentStatus(user.getEmail())).thenReturn(true);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket boughtTicket = ticketService.buyTicket(user.getEmail(), TicketType.STUDENT);

        assertNotNull(boughtTicket);
        assertEquals(TicketStatus.BOUGHT, boughtTicket.getStatus());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void testBuyTicket_NonStudentForStudentTicket() {
        user.setRole("USER");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                ticketService.buyTicket(user.getEmail(), TicketType.STUDENT)
        );

        assertEquals("Only STUDENT users can purchase this ticket.", ex.getMessage());
    }

    // -------- TEST ACTIVATE TICKET --------
    @Test
    void testActivateTicket_Success() {
        when(ticketRepository.findByQrCode(ticket.getQrCode())).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket activated = ticketService.activateTicket(ticket.getQrCode());

        assertEquals(TicketStatus.ACTIVE, activated.getStatus());
        assertNotNull(activated.getActivationDate());
        assertNotNull(activated.getExpirationDay());
    }

    @Test
    void testActivateTicket_AlreadyActive() {
        ticket.setStatus(TicketStatus.ACTIVE);
        when(ticketRepository.findByQrCode(ticket.getQrCode())).thenReturn(Optional.of(ticket));

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                ticketService.activateTicket(ticket.getQrCode())
        );

        assertEquals("Ticket already activated", ex.getMessage());
    }

    // -------- TEST CHECK VALIDITY --------
    @Test
    void testCheckValidity_ValidTicket() {
        ticket.setStatus(TicketStatus.ACTIVE);
        ticket.setActivationDate(new Date(System.currentTimeMillis() - 1000));
        ticket.setExpirationDay(new Date(System.currentTimeMillis() + 60000));

        when(ticketRepository.findByQrCode(ticket.getQrCode())).thenReturn(Optional.of(ticket));

        boolean valid = ticketService.checkValidity(ticket.getQrCode());

        assertTrue(valid);
    }

    @Test
    void testCheckValidity_ExpiredTicket() {
        ticket.setStatus(TicketStatus.ACTIVE);
        ticket.setActivationDate(new Date(System.currentTimeMillis() - 60000));
        ticket.setExpirationDay(new Date(System.currentTimeMillis() - 1000));

        when(ticketRepository.findByQrCode(ticket.getQrCode())).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        boolean valid = ticketService.checkValidity(ticket.getQrCode());

        assertFalse(valid);
        assertEquals(TicketStatus.EXPIRED, ticket.getStatus());
    }

    // -------- TEST GENERATE QR CODE --------
    @Test
    void testGenerateQRCode() {
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        String qrCode = ticketService.generateQRCode(ticket);

        assertNotNull(qrCode);
        assertEquals(qrCode, ticket.getQrCode());
    }
}
