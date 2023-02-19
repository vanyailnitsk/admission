package com.example.admission.models;

import jakarta.persistence.*;

@Entity
@Table
public class Faculty {
    @Id
    @SequenceGenerator(
            name = "faculty_sequence",
            sequenceName = "faculty_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "faculty_sequence"
    )
    private Integer facultyId;
    private String facultyName;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="universityId")
    private University university;

    public Faculty(Integer facultyId, String facultyName, University university) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.university = university;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public Faculty() {
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
