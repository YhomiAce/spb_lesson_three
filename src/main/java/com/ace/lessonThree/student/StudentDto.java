package com.ace.lessonThree.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
    @NotEmpty(message = "This field is required")
    String firstname,

    @NotEmpty(message = "This field is required")
    String lastname,

    @NotEmpty(message = "Email field is required")
    @Email
    String email,

    Integer schoolId,
    int age,
    double score

) {
    
}
