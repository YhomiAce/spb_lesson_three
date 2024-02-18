package com.ace.lessonThree.school;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(
            SchoolRepository repository,
            SchoolMapper schoolMapper) {
        this.schoolRepository = repository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolResponseDto create(SchoolDto schoolDto) {
        School school = schoolMapper.toSchool(schoolDto);
        School savedSchool = schoolRepository.save(school);
        return schoolMapper.toSchoolResponseDto(savedSchool);
    }

    public List<SchoolResponseDto> findAll() {
        List<School> schools = schoolRepository.findAll();
        return schools.stream().map(schoolMapper::toSchoolResponseDto).collect(Collectors.toList());
    }
}
