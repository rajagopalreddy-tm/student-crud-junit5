package com.trainingmug.student.app.controller;

import com.trainingmug.student.app.model.Student;
import com.trainingmug.student.app.service.StudentService;

import java.util.List;
import java.util.Optional;

public class StudentController {
    private StudentService service = new StudentService();

    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    public Optional<Student> getStudentById(int id) {
        return service.getStudentById(id);
    }

    public void addStudent(Student student) {
        service.addStudent(student);
    }

    public void deleteStudent(int id) {
        service.deleteStudent(id);
    }
}
