package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.savina.ticketsystem.repository.StudentRepository;
import java.time.LocalDate;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public boolean verifyStudentStatus(String email) {
        int currentYear = LocalDate.now().getYear();

        // Kërkon studentin nga email
        Optional<Student> optionalStudent = studentRepository.findByEmail(email);

        if (optionalStudent.isEmpty()) {
            System.out.println("Studenti nuk ekziston");
            return false;
        }

        Student student = optionalStudent.get();

        // Kontrollon nëse studenti është aktiv sipas vitit akademik
        if (currentYear >= student.getYearOfEnrolment() && currentYear <= student.getYearOfGraduation()) {
            System.out.println("Ky student ekziston dhe është aktiv");
            return true;
        } else {
            student.setStatus("0"); // shënon jo aktiv
            studentRepository.save(student);
            return false;
        }
    }







}
