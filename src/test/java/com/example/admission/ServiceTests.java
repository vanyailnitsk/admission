package com.example.admission;

import com.example.admission.models.User;
import com.example.admission.models.enums.Role;
import com.example.admission.services.FacultyService;
import com.example.admission.services.UniversityService;
import com.example.admission.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ServiceTests {
    private final UserService userService;
    private final CourseService courseService;
    private final UniversityService universityService;
    private final FacultyService facultyService;

    @Autowired
    public ServiceTests(UserService userService, CourseService courseService, UniversityService universityService, FacultyService facultyService) {
        this.userService = userService;
        this.courseService = courseService;
        this.universityService = universityService;
        this.facultyService = facultyService;
    }

    @Test
    void createUser() {
        User user = new User("new","user","email",
                "pass", LocalDate.of(2003, Month.JULY,7),
                269);
        userService.createUser(user);
        assertTrue(userService.getUserById(1L).getRoles().contains(Role.ROLE_USER));
    }
}
