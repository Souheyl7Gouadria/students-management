package com.example.demo.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public Student.StudentResponseDto saveStudent (StudentDto dto){
        var student = studentMapper.toStudent(dto);
        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentresponseDto(savedStudent);
    }

    public List<Student.StudentResponseDto> findAllStudents(){
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentresponseDto)
                .collect(Collectors.toList());
    }
    public Student.StudentResponseDto findStudentById(int id){
        return studentRepository.findById(id)
                .map(studentMapper::toStudentresponseDto)
                .orElse(null);
    }
    public List<Student.StudentResponseDto> findStudentsByName(String name){
        return studentRepository.findAllByFirstnameContaining(name)
                .stream()
                .map(studentMapper::toStudentresponseDto)
                .collect(Collectors.toList());
    }
    public void deleteStudent(int id){
        studentRepository.deleteById(id);
    }
}
