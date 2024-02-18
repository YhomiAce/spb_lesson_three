package com.ace.lessonThree.student;

public record StudentDto(
    String firstname,
    String lastname,
    String email,
    Integer schoolId,
    int age,
    double score

) {
    
}
