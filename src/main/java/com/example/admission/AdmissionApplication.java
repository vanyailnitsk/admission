package com.example.admission;

import com.example.admission.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
@SpringBootApplication
public class AdmissionApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AdmissionApplication.class, args);
	}

}
