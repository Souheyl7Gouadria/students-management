package com.example.demo.school;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {

    private final SchoolService schoolService;


    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    @Transactional
    public SchoolDto createSchool(@RequestBody School school){
        return schoolService.createSchool(school);
    }



    @GetMapping("/schools")
    public List<SchoolDto> findAllschools(){
        return schoolService.findAllSchools();
    }
}
