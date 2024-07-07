package com.example.demo.student;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentMapperTest {

    private StudentMapper studentMapper;
    @BeforeEach
    void setUp(){
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent(){
        // given
        StudentDto dto = new StudentDto("John", "Doe", "john@example.com", 1);
        // when
        Student student = studentMapper.toStudent(dto);
        // then
        Assertions.assertEquals(dto.firstname(), student.getFirstname());
        Assertions.assertEquals(dto.lastname(), student.getLastname());
        Assertions.assertNotNull(student.getSchool());
        Assertions.assertEquals(dto.schoolId(), student.getSchool().getId());

    }



}