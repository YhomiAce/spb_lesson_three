package com.ace.lessonThree;

public record StudentResponseDto(
    String firstname,
    String lastname,
    String email,
    int age,
    double score
) {
    
}
