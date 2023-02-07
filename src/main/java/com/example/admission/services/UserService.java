package com.example.admission.services;

import com.example.admission.models.User;
import com.example.admission.models.enums.Role;
import com.example.admission.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(()->new IllegalStateException("No user with id: "+id));
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        else return userRepository.findByEmail(principal.getName());
    }
}
