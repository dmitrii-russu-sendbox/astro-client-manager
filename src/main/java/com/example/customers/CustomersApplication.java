package com.example.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CustomersApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomersApplication.class, args);
	}

}
