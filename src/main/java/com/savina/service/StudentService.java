package com.savina.service;

import com.savina.model.Student;

public class StudentService {

    public boolean verifyStudentStatus(Student student) {
        // logjikë për të kontrolluar statusin e studentit
        return true;
    }

    public boolean updateStatus(Student student, String newStatus) {
        student.setStatus(newStatus);
        return true;
    }
}
