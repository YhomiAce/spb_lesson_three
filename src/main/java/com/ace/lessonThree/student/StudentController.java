package com.ace.lessonThree.student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(
        StudentService studentService
    ){
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto createStudent(
        @Valid @RequestBody StudentDto studentDto
    ) {
        return this.studentService.saveStudent(studentDto);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudent() {
       return studentService.findAllStudent();
    }

    @GetMapping("/students/{id}")
    public StudentResponseDto findStudentById(
        @PathVariable("id") Integer id
    ) {
        return this.studentService.findStudentById(id);
    }

    @GetMapping("/students/search")
    public List<StudentResponseDto> findStudentsByName(
        @RequestParam("name") String search
    ) {
        return studentService.findStudentsByFirstName(search);
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(
        @PathVariable("id") Integer id
    ){
        String resposne = this.studentService.deleteStudent(id);
        return ResponseEntity.ok(resposne);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<Map<String, String>>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
