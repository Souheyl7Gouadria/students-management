package com.example.demo.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

    public SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getName());
    }
}
