package com.example.admission.controllers;

import com.example.admission.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
//@RequestMapping(path = "/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPanel(Model model, Principal principal) {
        model.addAttribute("users",userService.getAll());
        return "admin";
    }
    @PostMapping("/admin/user/ban/{id}")
    public String banUser(Model model, Principal principal, @PathVariable("id") int id) {
        model.addAttribute("users",userService.getAll());
        userService.banUser(id);
        return "admin";
    }
}
