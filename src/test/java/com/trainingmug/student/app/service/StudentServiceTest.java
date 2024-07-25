package com.trainingmug.student.app.service;

import com.trainingmug.student.app.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private StudentService service;

    @BeforeEach
    void setUp() {
        service = new StudentService();
    }

    @Test
    void testGetAllStudents() {
        assertEquals(4, service.getAllStudents().size());
    }

    @Test
    void testGetStudentById() {
        Optional<Student> student = service.getStudentById(1);
        assertTrue(student.isPresent());
        assertEquals("John Doe", student.get().getName());
    }

    @Test
    void testAddStudent() {
        Student newStudent = new Student(4, "Anna Scott", 23);
        service.addStudent(newStudent);
        assertEquals(5, service.getAllStudents().size());
    }

    @Test
    void testDeleteStudent() {
        service.deleteStudent(1);
        assertEquals(3, service.getAllStudents().size());
        Optional<Student> student = service.getStudentById(1);
        assertFalse(student.isPresent());
    }
}
