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

    /**
     * Ruan validation log me të gjitha fushat.
     * validationDate do vendoset automatikisht nga DB (SYSDATE)
     */
    public ValidationLog saveValidation(Ticket ticket, String deviceID, String status, String qrCode) {
        ValidationLog log = new ValidationLog();
        log.setTicket(ticket);
        log.setDeviceID(deviceID);  // vendos deviceID si string
        log.setStatus(status);
        log.setQrCode(qrCode);

        // validationDate vendoset automatikisht nga DB (SYSDATE)
        return validationLogRepository.save(log);
    }

    /**
     * Merr të gjitha validation log-et
     */
    public List<ValidationLog> getAllLogs() {
        return validationLogRepository.findAll();
    }
}
