package com.ace.lessonThree.student;

import org.springframework.stereotype.Service;

import com.ace.lessonThree.school.School;

@Service
public class StudentMapper {
    public Student toStudent(StudentDto dto) {
        if(dto == null) {
            throw new NullPointerException("The Student Dto should not be null");
        }
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
        return new StudentResponseDto(student.getFirstname(), student.getLastname(),
                student.getEmail(), student.getAge(), student.getScore());
    }
}
