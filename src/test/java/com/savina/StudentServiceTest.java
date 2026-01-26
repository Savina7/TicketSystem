package com.savina;

import com.savina.ticketsystem.model.Student;
import com.savina.ticketsystem.repository.StudentRepository;
import com.savina.ticketsystem.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testVerifyStudentStatus_ActiveStudent_ReturnsTrue() {
        int currentYear = LocalDate.now().getYear();
        Student student = new Student();
        student.setEmail("student@example.com");
        student.setYearOfEnrolment(currentYear - 1);
        student.setYearOfGraduation(currentYear + 1);
        student.setStatus("1");

        when(studentRepository.findByEmail("student@example.com"))
                .thenReturn(Optional.of(student));

        boolean result = studentService.verifyStudentStatus("student@example.com");

        assertTrue(result);
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    public void testVerifyStudentStatus_NotActiveStudent_ReturnsFalse() {
        int currentYear = LocalDate.now().getYear();
        Student student = new Student();
        student.setEmail("student@example.com");
        student.setYearOfEnrolment(currentYear - 3);
        student.setYearOfGraduation(currentYear - 1);
        student.setStatus("1");

        when(studentRepository.findByEmail("student@example.com"))
                .thenReturn(Optional.of(student));

        boolean result = studentService.verifyStudentStatus("student@example.com");

        assertFalse(result);
        assertEquals("0", student.getStatus());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testVerifyStudentStatus_StudentNotFound_ReturnsFalse() {
        when(studentRepository.findByEmail("nonexistent@example.com"))
                .thenReturn(Optional.empty());

        boolean result = studentService.verifyStudentStatus("nonexistent@example.com");

        assertFalse(result);
        verify(studentRepository, never()).save(any(Student.class));
    }
}
