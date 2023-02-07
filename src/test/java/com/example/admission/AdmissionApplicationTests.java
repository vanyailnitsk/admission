package com.example.admission;

import com.example.admission.models.User;
import com.example.admission.models.enums.Role;
import com.example.admission.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
class AdmissionApplicationTests {

	@Test
	void contextLoads() {
	}


}
