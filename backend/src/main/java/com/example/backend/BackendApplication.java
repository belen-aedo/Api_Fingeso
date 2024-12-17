package com.example.backend;

import com.example.backend.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BackendApplication {
	// public class BackendApplication implements CommandLineRunner

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
