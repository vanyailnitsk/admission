package com.example.admission;

import com.example.admission.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.io.*;
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AdmissionApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AdmissionApplication.class, args);
	}

}
