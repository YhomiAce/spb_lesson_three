package com.ace.lessonThree.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public SchoolResponseDto toSchoolResponseDto(School school) {
        return new SchoolResponseDto(school.getId(), school.getName());
    }

    public School toSchool(SchoolDto dto) {
        School school = new School();
        school.setName(dto.name());
        return school;
    }
}
