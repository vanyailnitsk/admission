package com.example.admission.controllers;

import com.example.admission.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ApiController {

    @GetMapping("/student")
    public List<User> getStudents() {
        User user = new User(
                "new","user","email@yandex.ru",
                "pass", LocalDate.of(2003, Month.JULY,7),
                269);
        User user1 = new User(
                "vanya","user","email@yandex.ru",
                "pass", LocalDate.of(2003, Month.JULY,7),
                269);
        return List.of(user,user1);
    }
}
