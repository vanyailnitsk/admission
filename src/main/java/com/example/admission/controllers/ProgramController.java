package com.example.admission.controllers;

import com.example.admission.models.Program;
import com.example.admission.repositories.ProgramRepository;
import com.example.admission.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping("/")
    public String getProgramsList(Model model) {
        model.addAttribute("programs",programService.getAllPrograms());
        return "programs";
    }
}
