package org.airtribe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication// Combines @EnableAutoConfiguration, @ComponentScan, @Configuration
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystem.class, args);
    }
}