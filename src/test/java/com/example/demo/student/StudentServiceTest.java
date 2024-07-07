package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

// to test the studentService we need to mock the dependencies(repo and mapper)
class StudentServiceTest {

    // which service we want to test
    @InjectMocks
    private StudentService studentService;

    // declare the dependencies
    @Mock // tell the Test class that we want to mock that
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void should_Save_Student(){
        //given
        StudentDto  studentDto = new StudentDto("john" , "morata" , "john.morata@gmail.com",1);
        Student student = new Student("john","morata", "john.morata@gmail.com",20);
        Student savedStudent = new Student("john","morata", "john.morata@gmail.com",20);
        savedStudent.setId(1);
        // mock the calls
        when(studentMapper.toStudent(studentDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentresponseDto(savedStudent)).thenReturn(new Student.StudentResponseDto("john","morata"));
        //when
        Student.StudentResponseDto responseDto = studentService.saveStudent(studentDto);
        
        //then
        assertEquals(studentDto.firstname(), responseDto.firstname());
        assertEquals(studentDto.lastname(), responseDto.lastname());


    }



}