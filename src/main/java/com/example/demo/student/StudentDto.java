package com.example.demo.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
         @NotEmpty(message = "First name should not be empty")
         String firstname,
         @NotEmpty
         String lastname,
         String email,
         Integer schoolId
) {
}