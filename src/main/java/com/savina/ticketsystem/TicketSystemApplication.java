package com.savina.ticketsystem;

import com.savina.ticketsystem.model.*;
import com.savina.ticketsystem.service.CompanyService;
import com.savina.ticketsystem.service.ReportService;
import com.savina.ticketsystem.service.StudentService;
import com.savina.ticketsystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner testRegisterUser(UserService userService) {
        return args -> {

            String firstName = "Altea";
            String lastName = "Kostallari";
            String email = "altea.kostallari@outlook.com";
            String phone = "0684563782";
            String password = "test123";


            userService.registerUser(firstName, lastName, email, phone, password);

            // 3️⃣ Printo për të kontrolluar
            System.out.println("Useri u regjistrua me sukses!");
            System.out.println("Emri: " + firstName + " " + lastName);
            System.out.println("Email: " + email);
            System.out.println("Tel: " + phone);
        };
    }



}
