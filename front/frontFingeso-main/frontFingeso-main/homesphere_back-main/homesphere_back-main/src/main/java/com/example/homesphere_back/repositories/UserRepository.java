package com.example.homesphere_back.repositories;

import com.example.homesphere_back.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByRut(String rut);
    Users findByNumber(String number);
    Users findByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM Users u")
    List<Users> obtainAll();
}

