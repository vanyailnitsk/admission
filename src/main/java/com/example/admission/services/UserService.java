package com.example.admission.services;

import com.example.admission.models.User;
import com.example.admission.models.enums.Role;
import com.example.admission.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        return true;
    }
}
