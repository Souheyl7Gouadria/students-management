package com.example.demo.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {


    private final StudentService studentService;
    private final View error;

    public StudentController(StudentService studentService, View error) {
        this.studentService = studentService;
        this.error = error;
    }

    @PostMapping("/students")
    public Student.StudentResponseDto post (@Valid @RequestBody StudentDto dto ){
        return studentService.saveStudent(dto);
    }



    @GetMapping("/students")
    public List<Student.StudentResponseDto> findAllStudents(){
        return studentService.findAllStudents();
    }

    @GetMapping("/students/{student-id}")
    public Student.StudentResponseDto findStudentByID(@PathVariable("student-id") int id){
        return studentService.findStudentById(id);
    }


    @GetMapping("/students/search/{student-name}")
    public List<Student.StudentResponseDto> findStudentsByName(@PathVariable("student-name") String name){
        return studentService.findStudentsByName(name);
    }

    @DeleteMapping("/students/delete/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("student-id") Integer id){

        studentService.deleteStudent(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException exp){
                var errors = new HashMap<String,String>();
                exp.getBindingResult()
                        .getAllErrors()
                        .forEach(error -> {
                            var fieldName = ((FieldError) error).getField();
                            var errorMessage = error.getDefaultMessage();
                            errors.put(fieldName,errorMessage);
                        });
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



}
