package com.ace.lessonThree;

import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student toStudent(StudentDto dto) {
        Student student = new Student();
        student.setAge(dto.age());
        student.setFirstname(dto.firstname());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());
        student.setScore(dto.score());

        School school = new School();
        school.setId(dto.schoolId());
        student.setSchool(school);

        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto(student.getFirstname(), student.getLastname(),
                student.getEmail(), student.getAge(), student.getScore());
        return studentResponseDto;
    }
}
