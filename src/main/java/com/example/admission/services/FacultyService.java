package com.example.admission.services;

import com.example.admission.models.Faculty;
import com.example.admission.repositories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public void addNewFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }
}
