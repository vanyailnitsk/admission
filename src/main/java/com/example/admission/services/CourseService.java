package com.example.admission.services;

import com.example.admission.models.Course;
import com.example.admission.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void fillTheTable(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Map<String,String> map = new HashMap<>();
            while (reader.ready()) {
                String[] str = reader.readLine().split(" — ");
                courseRepository.save(new Course(str[0],str[1]));
            }
        }
        catch (IOException e) {
            System.out.println("File error!");
        }
    }
}
