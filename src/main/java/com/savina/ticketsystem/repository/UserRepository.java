package com.savina.ticketsystem.repository;

import com.savina.ticketsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Kjo mbetet për kontrollet e thjeshta gjatë regjistrimit
    Optional<User> findByEmail(String email);

    // Kjo është ajo që duhet të shtosh për LOGIN-in tënd
    @Query("SELECT u FROM User u " +
            "LEFT JOIN Student s ON s.userID = u.userID " +
            "LEFT JOIN Admin a ON a.userID = u.userID " +
            "WHERE u.password = :password " +
            "AND (u.email = :id OR u.phoneNumber = :id OR s.nrMatrikullimi = :id OR a.adminIdNumber = :id) " +
            "AND u.status = '1'")
    Optional<User> findForLogin(@Param("id") String identifier, @Param("password") String password);
}
