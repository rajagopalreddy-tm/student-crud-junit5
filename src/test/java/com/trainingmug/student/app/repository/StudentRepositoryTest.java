package com.trainingmug.student.app.repository;

import com.trainingmug.student.app.exception.StudentNotFoundException;
import com.trainingmug.student.app.model.Student;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new StudentRepository();
        studentRepository.addStudent(new Student(1, "Luffy", 20));
        studentRepository.addStudent(new Student(2, "Zoro", 22));
    }

    @Test
    @DisplayName("Test Add Student")
    void testAddStudent() {
        Student student = new Student(3, "Robin", 23);
        studentRepository.addStudent(student);

        List<Student> students = studentRepository.getAllStudents();
        assertEquals(3, students.size());
        assertTrue(students.contains(student));
    }

    @Test
    @DisplayName("Test Get All Students")
    void testGetAllStudents() {
        List<Student> students = studentRepository.getAllStudents();
        assertEquals(2, students.size());
    }

    @Test
    @DisplayName("Test Get Student By ID")
    void testGetStudentById() {
        Optional<Student> student = studentRepository.getStudentById(1);
        assertTrue(student.isPresent());
        assertEquals("Luffy", student.get().getName());
    }

    @Test
    @DisplayName("Test Get Student By ID Not Found")
    void testGetStudentByIdNotFound() {
        Optional<Student> student = studentRepository.getStudentById(3);
        assertFalse(student.isPresent());
    }

    @Test
    @DisplayName("Test Update Student Data")
    void testUpdateStudentData() throws StudentNotFoundException {
        Student updatedStudent = new Student(1, "Nami", 21);
        studentRepository.updateStudentData(updatedStudent, 1);

        Optional<Student> student = studentRepository.getStudentById(1);
        assertTrue(student.isPresent());
        assertEquals(21, student.get().getAge());
    }

    @Test
    @DisplayName("Test Update Student Data Not Found")
    void testUpdateStudentDataNotFound() {
        Student updatedStudent = new Student(3, "Chopper", 23);
        Exception exception = assertThrows(StudentNotFoundException.class, () -> {
            studentRepository.updateStudentData(updatedStudent, 3);
        });

        String expectedMessage = "Student with ID 3 not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Test Delete Student By ID")
    void testDeleteStudentById() throws StudentNotFoundException {
        studentRepository.deleteStudentById(1);

        Optional<Student> student = studentRepository.getStudentById(1);
        assertFalse(student.isPresent());
    }

    @Test
    @DisplayName("Test Delete Student By ID Not Found")
    void testDeleteStudentByIdNotFound() {
        Exception exception = assertThrows(StudentNotFoundException.class, () -> {
            studentRepository.deleteStudentById(3);
        });

        String expectedMessage = "Student with ID 3 not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @AfterEach
    void tearDown() {
        studentRepository.getAllStudents().clear();
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("All tests completed.");
    }
}
