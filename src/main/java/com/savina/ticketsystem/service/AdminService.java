package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.*;
import com.savina.ticketsystem.repository.CompanyRepository;
import com.savina.ticketsystem.repository.TicketRepository;
import com.savina.ticketsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository; // CRUD mbi user

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private BusService busService; // shtim kompanie

    @Autowired
    private ReportService reportService; // gjenerim raporti

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private TicketRepository ticketRepository; // <- duhet


    // -------------------------
    // DELETE USER (vetem admin)
    // -------------------------
    public void deleteUser(Admin admin, Long userId) {
        if (!"ADMIN".equals(admin.getRole())) {
            throw new RuntimeException("Vetem admin mund te fshije user");
        }
        userRepository.deleteById(userId);
    }

    // -------------------------
    // ADD COMPANY (vetem admin)
    // -------------------------
    public Company addCompany(Admin admin, String companyName, String companyEmail) {
        if (!"ADMIN".equals(admin.getRole())) {
            throw new RuntimeException("Vetem admin mund te shtoje kompani");
        }
        return companyService.createCompany(companyName, companyEmail);
    }

    // -------------------------
    // GENERATE REPORT (vetem admin)
    // -------------------------
    public Report generateReport(Admin admin, ReportType type, ReportPeriod period, Company company, User user) {
        if (!"ADMIN".equals(admin.getRole())) {
            throw new RuntimeException("Vetem admin mund te gjeneroje raport");
        }

        // Përdor ReportService që ke bërë
        return reportService.registerReport(type, period, company, user);
    }
    public List<Ticket> getAllTickets(Admin admin) {
        if (!"ADMIN".equals(admin.getRole())) {
            throw new RuntimeException("Vetëm admin mund të shikojë të gjitha biletat");
        }
        return ticketRepository.findAll();
    }

    public Device addDeviceToBus(Admin admin, String deviceType, Float serialNumber, Bus bus) {
        if (!"ADMIN".equals(admin.getRole())) {
            throw new RuntimeException("Vetëm admin mund të shtojë device");
        }

        return deviceService.addDevice(deviceType, serialNumber, bus);
    }

    // AdminService.java
    public Bus addBusToCompany(Admin admin, String companyName) {
        if (!"ADMIN".equals(admin.getRole())) {
            throw new RuntimeException("Vetëm admin mund të shtojë bus");
        }

        // Merr kompaninë
        Company company = companyRepository.findByCompanyName(companyName)
                .orElseThrow(() -> new RuntimeException("Kompania me kete emer nuk u gjet"));

        // Thërras metodën e BusService
        return BusService.addBus(company); // busService është i Autowired në AdminService
    }
    // AdminService.java
    public Bus updateBusStatus(Admin admin, Integer busId, String newStatus) {
        if (!"ADMIN".equals(admin.getRole())) {
            throw new RuntimeException("Vetëm admin mund të ndryshojë statusin e bus");
        }
        return busService.updateStatus(busId, newStatus); // thërret metodën në BusService
    }

    public Device updateDeviceStatus(Admin admin, Long deviceId, String deviceType, Float serialNumber, Bus bus, String status) {
        if (!"ADMIN".equals(admin.getRole())) {
            throw new RuntimeException("Vetëm admin mund të ndryshojë device");
        }
        return deviceService.updateDevice(deviceId, deviceType, serialNumber, bus, status);
    }


}
