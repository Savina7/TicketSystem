package com.savina;


import com.savina.ticketsystem.repository.UserRepository;
import com.savina.ticketsystem.service.UserService;
import com.savina.ticketsystem.model.*;
import com.savina.ticketsystem.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentIdRepository studentIdRepository;


    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testRegisterUser_Success() {
        String email = "user@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        userService.registerUser("John", "Doe", email, "1234567890", "password");

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_EmailExists() {
        String email = "user@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        userService.registerUser("John", "Doe", email, "1234567890", "password");

        verify(userRepository, never()).save(any(User.class));
    }


    @Test
    void testRegisterStudent_Success() {
        String email = "student@example.com";
        StudentId studentId = new StudentId();
        studentId.setStudentIdNumber("S123");
        studentId.setStudyProgram("Computer Science");
        studentId.setYearOfEnrollment(2022);
        studentId.setYearOfGraduation(2026);

        when(studentIdRepository.findByStudentNameAndStudentSurnameAndEmail("Jane", "Doe", email))
                .thenReturn(Optional.of(studentId));
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        userService.registerStudent("Jane", "Doe", email, "1234567890", "password");

        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testRegisterStudent_InvalidData() {
        String email = "student@example.com";
        when(studentIdRepository.findByStudentNameAndStudentSurnameAndEmail("Jane", "Doe", email))
                .thenReturn(Optional.empty());

        userService.registerStudent("Jane", "Doe", email, "1234567890", "password");

        verify(studentRepository, never()).save(any(Student.class));
    }

    // -------- TEST LOGIN --------
    @Test
    void testLogin_Success() {
        String email = "user@example.com";
        String password = "password";
        User user = new User();
        user.setUserID(1L);
        user.setRole("user");

        when(userRepository.findForLogin(email, password)).thenReturn(Optional.of(user));

        String role = userService.login(email, password);

        assertEquals("user", role);
    }

    @Test
    void testLogin_Failure() {
        when(userRepository.findForLogin("wrong@example.com", "pass"))
                .thenReturn(Optional.empty());

        String role = userService.login("wrong@example.com", "pass");

        assertNull(role);
    }
}

