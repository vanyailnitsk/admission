package com.example.admission.controllers;

import com.example.admission.models.Faculty;
import com.example.admission.models.University;
import com.example.admission.models.User;
import com.example.admission.repositories.FacultyRepository;
import com.example.admission.services.CourseService;
import com.example.admission.services.FacultyService;
import com.example.admission.services.UniversityService;
import com.example.admission.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class PersonController {
    private final CourseService courseService;
    private final UniversityService universityService;
    private final FacultyService facultyService;
    private final UserService userService;
    private final Logger log = Logger.getLogger(PersonController.class.getName());

    @Autowired
    public PersonController(CourseService courseService, UniversityService universityService, FacultyService facultyService, UserService userService) {
        this.courseService = courseService;
        this.universityService = universityService;
        this.facultyService = facultyService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String helloPage() {
        courseService.fillTheTable("/Users/admin/IdeaProjects/TestingThings/text.txt");
        return "profile";
    }
    @GetMapping("/")
    public String config() {
        University university = new University(1,"Bonch");
        Faculty faculty = new Faculty(1,"IKSS",university);
        Faculty faculty1 = new Faculty(2,"RTS",university);
        universityService.addNewUniversity(university);
        facultyService.addNewFaculty(faculty);
        facultyService.addNewFaculty(faculty1);
        User user = new User(
                1L,"new","user","email",
                "pass", LocalDate.of(2003, Month.JULY,7),
                269);
        userService.createUser(user);
        return "profile";
    }

}
