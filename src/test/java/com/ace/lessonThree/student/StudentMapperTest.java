package com.ace.lessonThree.student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
//    @BeforeAll
//    static void beforeAll() {
//        System.out.println("Inside before all method");
//    }
//
//    @AfterAll
//    static void afterAll() {
//        System.out.println("Inside after all method");
//    }
//
//    @AfterEach
//    void tearDown() {
//        System.out.println("Inside after each method");
//    }
//
//    @Test
//    public void testMethod1() {
//        System.out.println("My first test method");
//    }
//
//    @Test
//    public void testMethod2() {
//        System.out.println("My second test method");
//    }
//
//    @BeforeEach
//    void setUp() {
//        System.out.println("Inside before each method");
//    }

    private  StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public  void shouldMapStudentDtoToStudent() {
        StudentDto dto = new StudentDto("John", "Doe", "john@email.com", 1, 25, 87.5);
        Student student = mapper.toStudent(dto);
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertEquals(dto.age(), student.getAge());
        assertEquals(dto.score(), student.getScore());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        Student student = new Student("Jane", "Smith", "jane@mail.com", 23, 56.8);
        StudentResponseDto responseDto = mapper.toStudentResponseDto(student);
        assertEquals(responseDto.firstname(), student.getFirstname());
        assertEquals(responseDto.lastname(), student.getLastname());
        assertEquals(responseDto.email(), student.getEmail());
        assertEquals(responseDto.score(), student.getScore());
        assertEquals(responseDto.age(), student.getAge());
    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("The Student Dto should not be null", exception.getMessage());
    }
}