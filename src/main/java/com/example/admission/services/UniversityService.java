package com.example.admission.services;

import com.example.admission.models.Faculty;
import com.example.admission.models.University;
import com.example.admission.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityService {
    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }
    public void addNewUniversity(University university) {
        universityRepository.save(university);
    }

    public List<Faculty> getUniversityFaculties(Integer id) {
        return universityRepository.findById(id).get().getFaculties();
    }
    public University getUniversity(Integer id) {
        return universityRepository.findById(id).get();
    }
}
