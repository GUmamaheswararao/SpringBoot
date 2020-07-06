package com.example.external;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExternalRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExternalRestApplication.class, args);
	}
	
}
