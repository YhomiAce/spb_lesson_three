package com.ace.lessonThree.student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentService(
            StudentRepository repository,
            StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(StudentDto studentDto) {
        Student student = studentMapper.toStudent(studentDto);
        Student savedStudent = repository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> findAllStudent() {
        List<Student> students = repository.findAll();
        // This
        // return students.stream().map(student ->
        // studentMapper.toStudentResponseDto(student))
        // .collect(Collectors.toList());

        // Or That
        return students.stream().map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto findStudentById(Integer id) {

        // This
        // Student student = repository.findById(id).orElse(new Student());
        // return studentMapper.toStudentResponseDto(student);

        // Or
        return repository.findById(id).map(studentMapper::toStudentResponseDto).orElse(null);

    }

    public List<StudentResponseDto> findStudentsByFirstName(String search) {
        List<Student> students = repository.findAllByFirstnameContaining(search);
        return students.stream().map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    // @SuppressWarnings("null")
    public String deleteStudent(Integer id) {
        repository.deleteById(id);
        return "Student deleted successfully";
    }
}
