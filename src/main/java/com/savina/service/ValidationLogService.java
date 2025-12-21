package com.savina.service;

import com.savina.model.ValidationLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ValidationLogService {

    private List<ValidationLog> logs = new ArrayList<>();

    public ValidationLog saveValidation(ValidationLog log) {
        log.setValidationDate(new Date());
        logs.add(log);
        return log;
    }

    public boolean checkValidity(ValidationLog log) {
        return "VALID".equalsIgnoreCase(log.getStatus());
    }

    public List<ValidationLog> readHistory() {
        return logs;
    }
}
