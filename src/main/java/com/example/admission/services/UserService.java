package com.example.admission.services;

import com.example.admission.models.Program;
import com.example.admission.models.User;
import com.example.admission.models.enums.Role;
import com.example.admission.repositories.ProgramRepository;
import com.example.admission.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProgramRepository programRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,ProgramRepository programRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.programRepository = programRepository;
    }


    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("Saving new User with email: {}",email);
        return true;
    }
    @Transactional
    public List<User> sort() {
        List<User> buffer = userRepository.findAll();
        for (int i = 0; i < buffer.size(); i++) {
            if (!buffer.get(i).isEntrant()) {
                buffer.remove(i);
                i--;
            }
        }
        List<Program> programs = programRepository.findAll();
        int extra=buffer.size();
        for(Program program:programs) {
            extra-=program.getBudgetPlaces();
        }
        System.out.println("Number of extra entrants: "+extra);
        boolean isLast = false;
        while (true) {
            Iterator<User> iterator = buffer.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                Program program = user.getNextProgramObject();
                program.addEntrant(user);
                iterator.remove();
            }
            for(Program program: programs) {
                List<User> entrants = program.getExtra();
                for (User user : program.getEntered()) {
                    //user.updateCurrentProgram();
                }
                buffer.addAll(entrants);
            }
            if (isLast) break;
            if (buffer.isEmpty()||buffer.size()==extra) {
                isLast=true;
            }
        }
        return buffer;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new IllegalStateException("No user with id: "+id));
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        else return userRepository.findByEmail(principal.getName());
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void banUser(long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getUserId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getUserId(), user.getEmail());
            }
            userRepository.save(user);
        }
    }
    public void solution() {
        List<User> user = userRepository.findAll();

    }
}
