package com.example.admission.configs;

import com.example.admission.models.User;
import com.example.admission.repositories.UserRepository;
import com.example.admission.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailsService();
//    }

    @Bean
    CommandLineRunner commandLineRunner (UserRepository repository) {
        return args -> {
            User user = new User(
                    "new","user","vanya",
                    "pass", LocalDate.of(2003, Month.JULY,7),
                    269);
            user.setPassword(passwordEncoder().encode("pass"));
            repository.saveAll(List.of(user));
        };

    }
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/").permitAll()
                        .anyRequest().authenticated())
//                .formLogin()
//                .loginPage("/login")
//                .successForwardUrl("/")
//                .and()
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager createUserDetailsManager() {
//        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
//        UserDetails user = User.builder()
//                .passwordEncoder(passwordEncoder)
//                .username("user1")
//                .password("user")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .passwordEncoder(passwordEncoder)
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }


}
