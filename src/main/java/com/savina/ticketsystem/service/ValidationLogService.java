package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.Ticket;
import com.savina.ticketsystem.model.ValidationLog;
import com.savina.ticketsystem.repository.ValidationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationLogService {

    @Autowired
    private ValidationLogRepository validationLogRepository;


    public ValidationLog saveValidation(Ticket ticket, String deviceID, String status, String qrCode) {
        ValidationLog log = new ValidationLog();
        log.setTicket(ticket);
        log.setDeviceID(deviceID);
        log.setStatus(status);
        log.setQrCode(qrCode);

        return validationLogRepository.save(log);
    }


        public List<ValidationLog> getAllLogs() {
        return validationLogRepository.findAll();
    }
}
