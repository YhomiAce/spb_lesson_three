package com.ace.lessonThree.student;

public record StudentResponseDto(
    String firstname,
    String lastname,
    String email,
    int age,
    double score
) {
    
}
