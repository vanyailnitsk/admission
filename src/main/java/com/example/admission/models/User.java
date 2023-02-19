package com.example.admission.models;

import com.example.admission.models.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true, updatable = false)
    private String email;
    private String password;
    private LocalDate dob;
    private Integer scores;
    private boolean isActive;
    private Integer currentProgram;
    @Transient
    private int currentPos;

//    @ElementCollection(targetClass = Program.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "entrants",
//            joinColumns = @JoinColumn(name = "user_id"))
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "entrants_programs",
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn(name = "program_id"),}
    )
    @OrderColumn(name="orderField")
    @JsonManagedReference
    private List<Program> programs;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();


    public Long getUserId() {
        return id;
    }

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, LocalDate dob, Integer scores) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.scores = scores;
        this.isActive = true;
        this.currentPos=0;
        this.currentProgram=0;
    }

    public User(Long id, String firstName, String lastName, String email, String password, LocalDate dob, Integer scores, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.scores = scores;
        this.roles = roles;
        this.isActive = true;
        this.currentPos=0;
        this.currentProgram=0;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
        currentProgram=programs.get(0).getProgramId();
    }
    public void addProgram(Program program) {
        if (programs==null) {
            programs=new ArrayList<>();
        }
        programs.add(program);
        //program.getEntrants().add(this);
    }

    public Program getNextProgramObject() {
        return programs.get(currentPos++);
    }
    public void updateCurrentProgram() {
        System.out.println(currentPos);
        currentProgram = getCurrentProgram();
    }

    public void setCurrentProgram(Integer currentProgram) {
        this.currentProgram = currentProgram;
    }

    public boolean isEntrant() {
        return roles.contains(Role.ROLE_ENTRANT);
    }
    public int getCurrentProgram() {
        if (currentPos>=programs.size()) {
            return -1;
        }
        return programs.get(currentPos).getProgramId();
    }
    public Program getCurrentProgramObject() {
        if (programs.size()==0||currentPos>programs.size()) {
            throw new IllegalStateException("no programs found");
        }
        else {
            return programs.get(currentPos);
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }


    public void setUserId(Long userId) {
        this.id = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
