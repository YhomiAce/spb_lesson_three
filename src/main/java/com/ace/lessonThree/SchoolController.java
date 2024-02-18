package com.ace.lessonThree;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService service) {
        this.schoolService = service;
    }

    @PostMapping("/schools")
    public SchoolResponseDto create(
            @RequestBody SchoolDto schoolDto) {
        return schoolService.create(schoolDto);
    }

    @GetMapping("/schools")
    public List<SchoolResponseDto> findAll() {
        return schoolService.findAll();
    }
}
