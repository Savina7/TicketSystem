package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.Student;
import com.savina.ticketsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.savina.ticketsystem.repository.StudentRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public boolean verifyStudentStatus(String name, String surname, String email) {
        int currentYear = LocalDate.now().getYear();

        Optional<Student> optionalStudent =
                studentRepository.findByStudentNameAndStudentSurnameAndEmail(name, surname, email);

        if (optionalStudent.isEmpty()) {
            System.out.println("Studenti nuk ekziston");
            return false;
        }

        Student student = optionalStudent.get();

        if (currentYear >= student.getYearOfEnrolment()
                && currentYear <= student.getYearOfGraduation()) {

            System.out.println("Ky student ekziston dhe është aktiv");
            return true;
        } else {
            student.setStatus("0");
            studentRepository.save(student);
            return false;
        }
    }
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        for (Student s : students) {
            System.out.println(
                    "Nr Matrikullimi: " + s.getNrMatrikullimi() +
                            " | Emri: " + s.getFirstName() +
                            " | Mbiemri: " + s.getLastName() +
                            " | Email: " + s.getEmail() +
                            " | Status: " + s.getStatus()
            );
        }

        return students;
    }



}
