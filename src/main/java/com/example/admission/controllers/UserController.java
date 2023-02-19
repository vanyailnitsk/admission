package com.example.admission.controllers;

import com.example.admission.models.User;
import com.example.admission.services.FacultyService;
import com.example.admission.services.UniversityService;
import com.example.admission.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;


@Controller
public class UserController {
    private final UniversityService universityService;
    private final FacultyService facultyService;
    private final UserService userService;

    @Autowired
    public UserController( UniversityService universityService, FacultyService facultyService, UserService userService) {
        this.universityService = universityService;
        this.facultyService = facultyService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String helloPage(Principal principal,Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user",user);
        //model.addAttribute("programs",user.getPrograms());
        //userService.sort();
        return "profile";
    }
    @GetMapping("/login")
    public String loginPage() {
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
    public String homePage() {
        return "home";
    }

}
