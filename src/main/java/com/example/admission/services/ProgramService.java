package com.example.admission.services;

import com.example.admission.models.Program;
import com.example.admission.models.User;
import com.example.admission.repositories.ProgramRepository;
import com.example.admission.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProgramService(ProgramRepository repository,UserRepository userRepository) {
        this.programRepository = repository;
        this.userRepository = userRepository;
    }
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }
    public List<User> getEntered(int  programId) {
        List<User> users = userRepository.findAll();
        List<User> res = new ArrayList<>();
        users.stream().filter(e -> e.getCurrentProgram()==programId).forEach(res::add);
        users.sort((a,b) -> b.getScores()-a.getScores());
        return res;
    }
}
