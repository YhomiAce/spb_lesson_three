package com.ace.lessonThree.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;
    // This service has dependencies injected
    // declare the dependencies
    @Mock
    private StudentRepository repository;
    @Mock
    private StudentMapper studentMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {
        // Given
        StudentDto dto = new StudentDto("John", "Doe", "john@email.com", 1, 25, 87.5);
        Student student = new Student("John", "Doe", "john@email.com", 25, 87.5);
        Student savedStudent = new Student("John", "Doe", "john@email.com", 25, 87.5);
        savedStudent.setId(1);

        // Mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(repository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(
                new StudentResponseDto("John", "Doe", "john@email.com", 25, 87.5));

        // When
        StudentResponseDto responseDto = studentService.saveStudent(dto);

        // Then
        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());
        assertEquals(dto.age(), responseDto.age());

        verify(studentMapper, times(1)).toStudent(dto);
        verify(repository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void should_return_all_students() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(
                new Student("John", "Doe", "john@email.com", 25, 87.5)
        );

        // Mock the calls
        when(repository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn(
                new StudentResponseDto("John", "Doe", "john@email.com", 25, 87.5)
        );

        // When
        List<StudentResponseDto> responseDtos = studentService.findAllStudent();
        assertEquals(students.size(), responseDtos.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_return_a_single_student_by_id() {
        // Given
        Integer studentId = 1;
        Student student = new Student("John", "Doe", "john@email.com", 25, 87.5);
        student.setId(1);

        // Mock the calls
        when(repository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(student)).thenReturn(
                new StudentResponseDto("John", "Doe", "john@email.com", 25, 87.5)
        );

        // When
        StudentResponseDto dto = studentService.findStudentById(studentId);

        // Then

        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertEquals(dto.age(), student.getAge());

        verify(repository, times(1)).findById(studentId);
        verify(studentMapper, times(1)).toStudentResponseDto(student);
    }

    @Test
    public void should_find_students_by_firstname() {
        // Given
        String search = "John";
        List<Student> students = new ArrayList<>();
        students.add(
                new Student("John", "Doe", "john@email.com", 25, 87.5)
        );
        students.add(
                new Student("John", "Snow", "snow@email.com", 24, 77.5)
        );

        // Mock the calls
        when(repository.findAllByFirstnameContaining(search)).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn(
                new StudentResponseDto("John", "Doe", "john@email.com", 25, 87.5)
        );

        // When
        List<StudentResponseDto> responseDtos = studentService.findStudentsByFirstName(search);
        assertEquals(students.size(), responseDtos.size());

        verify(repository, times(1)).findAllByFirstnameContaining(search);
//        verify(studentMapper, times(students.size())).toStudentResponseDto(students.);
    }

    @Test
    public void should_delete_student_and_return_success_message(){
        // Given
        Integer studentId = 1;

        // Mock the calls
//        when(repository.deleteById(studentId)).then(void);

        // When
        String response = studentService.deleteStudent(studentId);

        assertEquals("Student deleted successfully", response);
    }
}