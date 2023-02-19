package com.example.admission.controllers;

import com.example.admission.models.Program;
import com.example.admission.models.User;
import com.example.admission.repositories.UserRepository;
import com.example.admission.services.ProgramService;
import com.example.admission.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ApiController {

    private final UserService userService;
    private final ProgramService programService;

    @Autowired
    public ApiController(UserService userService,ProgramService programService) {
        this.userService = userService;
        this.programService = programService;
    }
    @GetMapping("/student")
    public List<User> getStudents() {
        return userService.getAll();
    }
    @GetMapping("/student/{id}")
    public User getUser(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }
    @GetMapping("/programs")
    public List<Program> getPrograms() {
        return programService.getAllPrograms();
    }
    @GetMapping("/programs/{id}")
    public List<User> getEnteredUsers(@PathVariable("id") int id) {
        return programService.getEntered(id);
    }

}
