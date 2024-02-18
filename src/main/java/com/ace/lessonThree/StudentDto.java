package com.ace.lessonThree;

public record StudentDto(
    String firstname,
    String lastname,
    String email,
    Integer schoolId,
    int age,
    double score

) {
    
}
