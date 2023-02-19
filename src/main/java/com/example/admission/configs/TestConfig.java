package com.example.admission.configs;

import com.example.admission.models.Program;
import com.example.admission.models.User;
import com.example.admission.models.enums.Role;
import com.example.admission.repositories.ProgramRepository;
import com.example.admission.repositories.UniversityRepository;
import com.example.admission.repositories.UserRepository;
import com.example.admission.services.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Configuration
public class TestConfig {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public TestConfig(PasswordEncoder passwordEncoder,UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @Bean
    @Transactional
    CommandLineRunner commandLineRunner (UserRepository userRepo,
                                        UniversityRepository univRepo,
                                         ProgramRepository programRepo) {
    return args -> {
//        User user = new User(
//                "Ivan","Ilnitskiy","vanya",
//                "pass", LocalDate.of(2003, Month.JULY,7),
//                269);
//        user.setPassword(passwordEncoder.encode("pass"));
//        user.setRoles(Set.of(Role.ROLE_USER,Role.ROLE_ENTRANT));
        User admin = new User(
                "admin","admin","admin",
                "admin", LocalDate.of(2003, Month.JULY,7),
                269);
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(Set.of(Role.ROLE_ADMIN,Role.ROLE_USER));
        programRepo.saveAll(readSpecs("programs.txt"));
        userRepo.save(admin);
        userRepo.saveAll(readUsers("entrants.txt",programRepo));
        List<Program> lst = programRepo.findAll();
        //Hibernate.initialize();
        userService.sort();
    };
    }
    public List<Program> readSpecs(String fileName) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<Program> list = new ArrayList<>();
        while(reader.ready()) {
            String[] str = reader.readLine().split(" ");
            Program program = new Program(
                    Integer.parseInt(str[0]),
                    Integer.parseInt(str[1]),
                    str[2],
                    Integer.parseInt(str[3])
            );
            list.add(program);
        }
        reader.close();
        return list;
    }
    @Transactional
    public List<User> readUsers(String fileName,ProgramRepository repository) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<User> list = new ArrayList<>();
        while (reader.ready()) {
            String[] str = reader.readLine().split("\\|");
            User user = new User(
                    str[0],
                    str[1],
                    str[2],
                    passwordEncoder.encode(str[3]),
                    LocalDate.parse(str[4],DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    Integer.parseInt(str[5])
            );
            user.setRoles(Set.of(Role.ROLE_USER, Role.ROLE_ENTRANT));
            List<Program> programs = new ArrayList<>();
            for(String elem: str[6].split(" ")) {
                //programs.add(repository.findById(elem).get());
                user.addProgram(repository.findById(elem).get());
            }
            //user.setPrograms(programs);
            list.add(user);
        }
        return list;
    }

}
