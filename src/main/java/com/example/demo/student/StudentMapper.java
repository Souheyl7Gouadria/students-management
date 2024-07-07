package com.example.demo.student;


import com.example.demo.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    // transform the studentDto to Student obj to persist in the database
    public Student toStudent(StudentDto dto){
        var student = new Student();
        student.setFirstname(dto.firstname());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());
        var school = new School();
        school.setId(dto.schoolId());
        student.setSchool(school);
        return student;
    }
    // transform the Student to StudentResponseDto
    public Student.StudentResponseDto toStudentresponseDto(Student student){
        return new Student.StudentResponseDto(
                student.getFirstname(),
                student.getLastname()
        );
    }
}
