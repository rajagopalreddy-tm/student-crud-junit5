package com.trainingmug.student.app.repository;

import com.trainingmug.student.app.exception.StudentDataNullException;
import com.trainingmug.student.app.exception.StudentNotFoundException;
import com.trainingmug.student.app.model.Student;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() throws StudentDataNullException {
        studentRepository = new StudentRepository();
        studentRepository.addStudent(new Student(1, "Luffy", 20));
        studentRepository.addStudent(new Student(2, "Zoro", 22));
    }

    @Test
    @DisplayName("Test Add Student: Data Should Not Be Null")
    void testAddStudentDataShouldNotBeNull() {
        Student student = null;
        Exception exception = assertThrows(StudentDataNullException.class, () -> studentRepository.addStudent(student));
        assertEquals("Student cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Test Add Student: All Parameters Should Be Present")
    void testAddStudentAllParametersShouldBePresent() throws StudentDataNullException {
        Student student = new Student(3, "Robin", 23);
        studentRepository.addStudent(student);

        List<Student> students = studentRepository.getAllStudents();
        Student addedStudent = students.get(students.size() - 1);

        assertTrue(addedStudent.getId() > 0, "Student ID should be greater than 0");
        assertNotNull(addedStudent.getName(), "Student name should not be null");
        assertTrue(addedStudent.getAge() > 0, "Student age should be greater than 0");
    }

    @Test
    @DisplayName("Test Add Student: Data Should Be Added at the End of the List")
    void testAddStudentDataShouldBeAddedAtEndOfList() throws StudentDataNullException {
        Student student = new Student(3, "Robin", 23);
        studentRepository.addStudent(student);

        List<Student> students = studentRepository.getAllStudents();
        assertEquals(student, students.get(students.size() - 1), "Student should be added at the end of the list");
    }



    @Test
    @DisplayName("Test Get All Students: Data Should Not Be Null")
    void testGetAllStudentsDataShouldNotBeNull() {
        List<Student> students = studentRepository.getAllStudents();
        assertNotNull(students,"Student list should not be null");
    }

    @Test
    @DisplayName("Test Get All Students: All Parameters Should Be Present")
    void testGetAllStudentsAllParametersShouldBePresent() {
        List<Student> students = studentRepository.getAllStudents();
        for (Student student : students) {
            assertTrue(student.getId() > 0, "Student ID should be a positive integer");
            assertNotNull(student.getName(), "Student name should not be null");
            assertTrue(student.getAge() > 0, "Student age should be a positive integer");
        }
    }

    @Test
    @DisplayName("Test Get All Students: Check the Size of the Data")
    void testGetAllStudentsCheckSize() {
        List<Student> students = studentRepository.getAllStudents();
        assertEquals(2,students.size(),"Student list size should be 2");
    }


    @Test
    @DisplayName("Test Get Student By ID")
    void testGetStudentById() throws StudentNotFoundException {
        Optional<Student> student = studentRepository.getStudentById(1);
        assertTrue(student.isPresent());
        assertEquals("Luffy", student.get().getName());
    }

    @Test
    @DisplayName("Test Get Student By ID: Return Type Should Be Optional")
    void testGetStudentByIdReturnTypeShouldBeOptional() throws StudentNotFoundException {
        Optional<Student> student = studentRepository.getStudentById(1);
        assertTrue(student instanceof Optional, "Return type should be Optional");
    }

    @Test
    @DisplayName("Test Get Student By ID: Data Should Not Be Null")
    void testGetStudentByIdDataShouldNotBeNull() throws StudentNotFoundException {
        Optional<Student> student = studentRepository.getStudentById(1);
        assertNotNull(student,"Student should not be null");
    }

    @Test
    @DisplayName("Test Get Student By ID: All Parameters Should Be Present")
    void testGetStudentByIdAllParametersShouldBePresent() throws StudentNotFoundException {
        Optional<Student> student = studentRepository.getStudentById(1);
        assertTrue(student.isPresent(),"Student should be present");

        Student studentData = student.get();
        assertTrue(studentData.getId()>0,"Student ID should be a positive integer");
        assertNotNull(studentData.getName(), "Student name should not be null");
        assertTrue(studentData.getAge() > 0, "Student age should be a positive integer");
    }

    @Test
    @DisplayName("Test Get Student By ID: Exception Should Be Thrown If Student Not Found")
    void testGetStudentByIdExceptionShouldBeThrownIfStudentNotFound() {
        int nonExistentId = 10;
        Exception exception = assertThrows(StudentNotFoundException.class,()->{
            studentRepository.getStudentById(nonExistentId);
        });
        assertEquals("Student with ID " + nonExistentId + " not found", exception.getMessage());

    }



    @Test
    @DisplayName("Test Update Student Data: Data Should Not Be Null")
    void testUpdateStudentDataDataShouldNotBeNull() {
        Student student = null;
        Exception exception = assertThrows(StudentDataNullException.class,()-> studentRepository.updateStudentData(student,1));
    }
//    @Test
//    @DisplayName("Test Update Student Data")
//    void testUpdateStudentData() throws StudentNotFoundException {
//        Student updatedStudent = new Student(1, "Nami", 21);
//        studentRepository.updateStudentData(updatedStudent, 1);
//
//        Optional<Student> student = studentRepository.getStudentById(1);
//        assertTrue(student.isPresent());
//        assertEquals(21, student.get().getAge());
//    }
//
//    @Test
//    @DisplayName("Test Update Student Data Not Found")
//    void testUpdateStudentDataNotFound() {
//        Student updatedStudent = new Student(3, "Chopper", 23);
//        Exception exception = assertThrows(StudentNotFoundException.class, () -> {
//            studentRepository.updateStudentData(updatedStudent, 3);
//        });
//
//        String expectedMessage = "Student with ID 3 not found";
//        String actualMessage = exception.getMessage();
//        assertTrue(actualMessage.contains(expectedMessage));
//    }

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
