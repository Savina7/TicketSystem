package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.Bus;
import com.savina.ticketsystem.model.Company;
import com.savina.ticketsystem.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService {

    @Autowired
    private static BusRepository busRepository;

    // -------------------------
    // Shtimi i autobusit (status gjithmonë 'A')
    // -------------------------
    public static Bus addBus(Company company) {
        Bus bus = new Bus();
        bus.setCompany(company);
        bus.setStatus("A"); // gjithmonë ACTIVE
        return busRepository.save(bus);
    }

    // -------------------------
    // Përditësimi i statusit
    // -------------------------
    public Bus updateStatus(Integer busId, String newStatus) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus nuk u gjet me id: " + busId));
        bus.setStatus(newStatus);
        return busRepository.save(bus);
    }
}
