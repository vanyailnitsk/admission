package com.example.admission.controllers;

import com.example.admission.models.Faculty;
import com.example.admission.models.University;
import com.example.admission.models.User;
import com.example.admission.services.CourseService;
import com.example.admission.services.FacultyService;
import com.example.admission.services.UniversityService;
import com.example.admission.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Logger;


@Controller
public class UserController {
    private final CourseService courseService;
    private final UniversityService universityService;
    private final FacultyService facultyService;
    private final UserService userService;

    @Autowired
    public UserController(CourseService courseService, UniversityService universityService, FacultyService facultyService, UserService userService) {
        this.courseService = courseService;
        this.universityService = universityService;
        this.facultyService = facultyService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String helloPage(Principal principal,Model model) {
        model.addAttribute("user",userService.getUserByPrincipal(principal));
        return "profile";
    }
    @GetMapping("/login")
    public String loginPage(Principal principal,Model model) {
        model.addAttribute(userService.getUserByPrincipal(principal));
        return "login";
    }
    @GetMapping("/registration")
    public String registerPage(Principal principal,Model model) {
        model.addAttribute("user",userService.getUserByPrincipal(principal));
        return "registration";
    }
    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
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
                "new","user","email@yandex.ru",
                "pass", LocalDate.of(2003, Month.JULY,7),
                269);
        userService.createUser(user);
        return "profile";
    }

}
