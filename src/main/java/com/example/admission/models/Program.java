package com.example.admission.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="programs")
public class Program {
    @Id
    @SequenceGenerator(
            name = "program_sequence",
            sequenceName = "program_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "program_sequence"
    )
    private int programId;
    private int universityId;
    private String programName;
    private int budgetPlaces;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
//            mappedBy = "entrants")
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "entrants_programs",
//            joinColumns = { @JoinColumn(name = "program_id") },
//            inverseJoinColumns = { @JoinColumn(name = "user_id"),}
//    )

    @ManyToMany(mappedBy = "programs",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<User> entrants;
    @Transient
    private List<User> entered;

    public Program() {
        this.entered=new ArrayList<>();
    }

    public Program(int universityId, String programName, int budgetPlaces) {
        this.universityId = universityId;
        this.programName = programName;
        this.budgetPlaces = budgetPlaces;
        this.entered=new ArrayList<>();
    }

    public Program(int programId, int universityId, String programName, int budgetPlaces) {
        this.programId = programId;
        this.universityId = universityId;
        this.programName = programName;
        this.budgetPlaces = budgetPlaces;
        this.entered=new ArrayList<>();
    }
    public List<User> getEntrants() {
        return entrants;
    }

    public void setEntrants(List<User> entrants) {
        this.entrants = entrants;
    }
    public void addEntrant(User user) {
        entered.add(user);
    }

    public List<User> getEntered() {
        return entered;
    }

    @JsonIgnore
    public List<User> getExtra() {
        entered.sort((u1, u2) -> u2.getScores() - u1.getScores());
        List<User> extra = new ArrayList<>();
        while (entered.size()>budgetPlaces) {
            extra.add(entered.remove(budgetPlaces));
        }
        return extra;
    }

    public Program(int programId, int universityId, String programName, int budgetPlaces,List<User> list) {
        this.programId = programId;
        this.universityId = universityId;
        this.programName = programName;
        this.budgetPlaces = budgetPlaces;
        this.entrants=list;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId=" + programId +
                ", universityId=" + universityId +
                ", programName='" + programName + '\'' +
                ", budgetPlaces=" + budgetPlaces +
                '}';
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getUnivId() {
        return universityId;
    }

    public void setUnivId(int universityId) {
        this.universityId = universityId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getBudgetPlaces() {
        return budgetPlaces;
    }

    public void setBudgetPlaces(int budgetPlaces) {
        this.budgetPlaces = budgetPlaces;
    }
}

