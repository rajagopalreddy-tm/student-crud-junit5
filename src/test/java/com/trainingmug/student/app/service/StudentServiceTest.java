package com.trainingmug.student.app.service;

import com.trainingmug.student.app.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private StudentService service;

    @BeforeEach
    void setUp() {
        service = new StudentService();
    }

    @Test
    @DisplayName("Test Get All Students")
    void testGetAllStudents() {
        assertEquals(4, service.getAllStudents().size(),"number of students should be 4");
    }

    @Test
    @DisplayName("Test Get Student by ID")
    void testGetStudentById() {
        Optional<Student> student = service.getStudentById(1);
        assertTrue(student.isPresent());
        assertEquals("John Doe", student.get().getName(),"student name should be equal to John Doe");
    }

    @Test
    @DisplayName("Test Add Student")
    void testAddStudent() {
        Student newStudent = new Student(4, "Anna Scott", 23);
        service.addStudent(newStudent);
        assertEquals(5, service.getAllStudents().size(),"number students should be 5");
    }

    @Test
    @DisplayName("Test Delete Student by ID")
    void testDeleteStudent() {
        service.deleteStudent(1);
        assertEquals(3, service.getAllStudents().size());
        Optional<Student> student = service.getStudentById(1);
        assertFalse(student.isPresent());
//        assertFalse(false, String.valueOf(student.isPresent()));
    }

    @Test
    @DisplayName("Sample Test case")
    void testAssertions() {

        assertEquals(4, 2 + 2, "2 + 2 should equal 4");

        assertNotEquals(5, 2 + 2, "2 + 2 should not equal 5");

        assertTrue(3 < 4, "3 should be less than 4");

        assertFalse(3 > 4, "3 should not be greater than 4");

        assertNull(null, "The object should be null");

        assertNotNull("Hello, World!", "The object should not be null");

        // Advanced assertions
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3}, "Arrays should be equal");

        assertThrows(ArithmeticException.class, () -> {
            int result = 1 / 0;
        }, "Division by zero should throw ArithmeticException");

        assertDoesNotThrow(() -> {
            int result = 1 + 1;
        }, "Adding two numbers should not throw an exception");

        assertTimeout(Duration.ofMillis(100), () -> {
            // some code that should run within 100 milliseconds
        }, "The code should execute within 100 milliseconds");

        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            // some code that should run within 100 milliseconds
        }, "The code should execute within 100 milliseconds");
    }

}
