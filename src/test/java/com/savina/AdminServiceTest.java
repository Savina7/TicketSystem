package com.savina;

import com.savina.ticketsystem.model.*;
import com.savina.ticketsystem.repository.*;
import com.savina.ticketsystem.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyService companyService;

    @Mock
    private BusService busService;

    @Mock
    private ReportService reportService;

    @Mock
    private DeviceService deviceService;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private AdminService adminService;

    private Admin admin;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        admin = new Admin();
        admin.setRole("ADMIN");

        user = new User();
        user.setUserID(1L);
    }


    @Test
    void deleteUser_AsAdmin_Success() {
        adminService.deleteUser(admin, 1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_NotAdmin_Throws() {
        admin.setRole("USER");
        RuntimeException ex = assertThrows(RuntimeException.class, () -> adminService.deleteUser(admin, 1L));
        assertEquals("Vetem admin mund te fshije user", ex.getMessage());
    }


    @Test
    void addCompany_AsAdmin_Success() {
        Company company = new Company();
        company.setCompanyName("TestCompany");
        when(companyService.createCompany("TestCompany", "email@test.com")).thenReturn(company);

        Company result = adminService.addCompany(admin, "TestCompany", "email@test.com");
        assertEquals("TestCompany", result.getCompanyName());
    }

    @Test
    void addCompany_NotAdmin_Throws() {
        admin.setRole("USER");
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> adminService.addCompany(admin, "TestCompany", "email@test.com"));
        assertEquals("Vetem admin mund te shtoje kompani", ex.getMessage());
    }


    @Test
    void generateReport_AsAdmin_Success() {
        Company company = new Company();
        User user = new User();
        Report report = new Report();
        when(reportService.registerReport(ReportType.SALES, ReportPeriod.MONTHLY, company, user)).thenReturn(report);

        Report result = adminService.generateReport(admin, ReportType.SALES, ReportPeriod.MONTHLY, company, user);
        assertNotNull(result);
        verify(reportService, times(1)).registerReport(ReportType.SALES, ReportPeriod.MONTHLY, company, user);
    }

    @Test
    void generateReport_NotAdmin_Throws() {
        admin.setRole("USER");
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                adminService.generateReport(admin, ReportType.SALES, ReportPeriod.MONTHLY, new Company(), new User()));
        assertEquals("Vetem admin mund te gjeneroje raport", ex.getMessage());
    }


    @Test
    void getAllTickets_AsAdmin_Success() {
        Ticket ticket = new Ticket();
        when(ticketRepository.findAll()).thenReturn(List.of(ticket));

        List<Ticket> tickets = adminService.getAllTickets(admin);
        assertEquals(1, tickets.size());
    }

    @Test
    void getAllTickets_NotAdmin_Throws() {
        admin.setRole("USER");
        RuntimeException ex = assertThrows(RuntimeException.class, () -> adminService.getAllTickets(admin));
        assertEquals("Vetëm admin mund të shikojë të gjitha biletat", ex.getMessage());
    }


    @Test
    void addDeviceToBus_AsAdmin_Success() {
        Bus bus = new Bus();
        Device device = new Device();
        when(deviceService.addDevice("Sensor", 1234f, bus)).thenReturn(device);

        Device result = adminService.addDeviceToBus(admin, "Sensor", 1234f, bus);
        assertNotNull(result);
    }

    @Test
    void addDeviceToBus_NotAdmin_Throws() {
        admin.setRole("USER");
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> adminService.addDeviceToBus(admin, "Sensor", 1234f, new Bus()));
        assertEquals("Vetëm admin mund të shtojë device", ex.getMessage());
    }




    @Test
    void updateBusStatus_AsAdmin_Success() {
        Bus bus = new Bus();
        when(busService.updateStatus(1, "ACTIVE")).thenReturn(bus);

        Bus result = adminService.updateBusStatus(admin, 1, "ACTIVE");
        assertNotNull(result);
    }

    @Test
    void updateBusStatus_NotAdmin_Throws() {
        admin.setRole("USER");
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> adminService.updateBusStatus(admin, 1, "ACTIVE"));
        assertEquals("Vetëm admin mund të ndryshojë statusin e bus", ex.getMessage());
    }


    @Test
    void updateDeviceStatus_AsAdmin_Success() {
        Bus bus = new Bus();
        Device device = new Device();
        when(deviceService.updateDevice(1L, "Sensor", 1234f, bus, "ACTIVE")).thenReturn(device);

        Device result = adminService.updateDeviceStatus(admin, 1L, "Sensor", 1234f, bus, "ACTIVE");
        assertNotNull(result);
    }

    @Test
    void updateDeviceStatus_NotAdmin_Throws() {
        admin.setRole("USER");
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> adminService.updateDeviceStatus(admin, 1L, "Sensor", 1234f, new Bus(), "ACTIVE"));
        assertEquals("Vetëm admin mund të ndryshojë device", ex.getMessage());
    }
}