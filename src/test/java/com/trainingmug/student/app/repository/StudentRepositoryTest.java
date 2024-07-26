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
        assertThrows(StudentDataNullException.class, () -> studentRepository.updateStudentData(student, 1));
    }

    @Test
    @DisplayName("Test Update Student Data: All Parameters Should Be Present")
    void testUpdateStudentDataAllParametersShouldBePresent() throws StudentNotFoundException, StudentDataNullException {
        Student studentData = new Student(1,"Monkey D Luffy", 21);
        studentRepository.updateStudentData(studentData,1);

        Student student = studentRepository.getStudentById(1).orElse(null);
        assertNotNull(student,"Updated student should not be null");
        assertEquals("Monkey D Luffy",student.getName(),"Student name should be updated");
        assertEquals(21,student.getAge(),"Student age should be updated");
    }

    @Test
    @DisplayName("Test Update Student Data: Exception Should Be Thrown If Student Not Found")
    void testUpdateStudentDataExceptionShouldBeThrownIfStudentNotFound() throws StudentNotFoundException {
        Student updatedStudent = new Student(10, "Nonexistent", 30);
        Exception exception = assertThrows(StudentNotFoundException.class, () ->
                studentRepository.updateStudentData(updatedStudent, 10));
        assertEquals("Student with ID " + 10 + " not found",exception.getMessage());
    }

    @Test
    @DisplayName("Test Update Student Data: Check If Data Is Updating")
    void testUpdateStudentDataCheckIfDataIsUpdating() throws StudentNotFoundException, StudentDataNullException {
        Student updatedStudent = new Student(2, "Roronoa Zoro", 23);
        studentRepository.updateStudentData(updatedStudent, 2);

        Student student = studentRepository.getStudentById(2).orElse(null);
        assertNotNull(student, "Updated student should not be null");
        assertEquals("Roronoa Zoro", student.getName(), "Student name should be updated");
        assertEquals(23, student.getAge(), "Student age should be updated");
    }


    @Test
    @DisplayName("Test Delete Student By ID: Data Should Be Deleted Successfully")
    void testDeleteStudentByIdDataShouldBeDeletedSuccessfully() throws StudentNotFoundException {
        studentRepository.deleteStudentById(1);
        assertEquals(1,studentRepository.getAllStudents().size(),"Student list size should be 1");
//        assertFalse(studentRepository.getStudentById(1).isPresent(), "Student size should be 1");
    }

    @Test
    @DisplayName("Test Delete Student By ID: Exception Should Be Thrown If Student Not Found")
    void testDeleteStudentByIdExceptionShouldBeThrownIfStudentNotFound() {
        int nonExistentId = 99;
        Exception exception = assertThrows(StudentNotFoundException.class, () -> {
            studentRepository.deleteStudentById(nonExistentId);
        });
        assertEquals("Student with ID " + nonExistentId + " not found", exception.getMessage());
    }

    @Test
    @DisplayName("Test Delete Student By ID: Check If Size of List is Decreasing")
    void testDeleteStudentByIdCheckIfSizeOfListIsDecreasing() throws StudentNotFoundException {
        int initialSize = studentRepository.getAllStudents().size();
        studentRepository.deleteStudentById(2);
        int newSize = studentRepository.getAllStudents().size();
        assertEquals(initialSize - 1, newSize, "Size of the list should decrease by 1 after deletion");
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
