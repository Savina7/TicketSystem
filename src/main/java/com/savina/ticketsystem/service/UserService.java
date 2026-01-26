package com.savina.ticketsystem.service;

import com.savina.ticketsystem.model.*;
import com.savina.ticketsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentIdRepository studentIdRepository;

    @Autowired
    private AdminIdRepository adminIdRepository;

    @Autowired
    private UserRepository userRepository;


    public void registerUser(String firstName, String lastName,
                             String email, String phone, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            System.out.println("User ekziston");
            return;
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setPassword(password);
        user.setRole("user");
        user.setStatus("1");

        userRepository.save(user);
        System.out.println("User u regjistrua");
    }


    public void registerStudent(String firstName, String lastName,
                                String email, String phone, String password) {

        var check = studentIdRepository
                .findByStudentNameAndStudentSurnameAndEmail(firstName, lastName, email);
        if (check.isEmpty()) {
            System.out.println("The provided data is invalid.");
            return;
        }
        if (userRepository.findByEmail(email).isPresent()) {
            System.out.println("An account with this email already exists.");
            return;
        }
        StudentId idInfo = check.get();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setPhoneNumber(phone);
        student.setPassword(password);
        student.setRole("student");
        student.setStatus("1");

        student.setNrMatrikullimi(idInfo.getStudentIdNumber());
        student.setStudyProgram(idInfo.getStudyProgram());
        student.setYearOfEnrolment(idInfo.getYearOfEnrollment());
        student.setYearOfGraduation(idInfo.getYearOfGraduation());

        studentRepository.save(student);
        System.out.println("The student has been successfully registered.");
    }


    public void registerAdmin(String firstName, String lastName,
                              String email, String phone, String password) {

        var check = adminIdRepository
                .findByAdminNameAndAdminSurnameAndEmail(firstName, lastName, email);

        if (check.isEmpty()) {
            System.out.println("Te dhenat nuk jane te sakta");
            return;
        }

        if (userRepository.findByEmail(email).isPresent()) {
            System.out.println("Email ekziston");
            return;
        }

        AdminId idInfo = check.get();


        Admin admin = new Admin();
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setEmail(email);
        admin.setPhoneNumber(phone);
        admin.setPassword(password);
        admin.setRole("admin");
        admin.setStatus("1");


        admin.setAdminIdNumber(idInfo.getAdminIdNumber());
        admin.setCompanyID(idInfo.getCompanyId());
        admin.setAdminStatus("1");

        adminRepository.save(admin);
        System.out.println("Admin u regjistrua");
    }

    public String login(String identifier, String password) {

        Optional<User> userOpt = userRepository.findForLogin(identifier, password);

        if (userOpt.isEmpty()) {
            System.out.println("Te dhena te gabuara!");
            return null;
        }

        User user = userOpt.get();

        System.out.println(
                "Login OK | USER_ID=" + user.getUserID() +
                        " | ROLE=" + user.getRole()
        );


        switch (user.getRole()) {
            case "user":
                System.out.println("Navigimi: User Dashboard");
                break;
            case "student":
                System.out.println("Navigimi: Student Dashboard");
                break;
            case "admin":
                System.out.println("Navigimi: Admin Dashboard");
                break;
            default:
                System.out.println("Role i panjohur");
        }

        return user.getRole();
    }
}
