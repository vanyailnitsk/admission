package com.example.admission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.io.*;
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AdmissionApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AdmissionApplication.class, args);
	}

}
