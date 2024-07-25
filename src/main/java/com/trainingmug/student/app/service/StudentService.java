package com.trainingmug.student.app.service;

import com.trainingmug.student.app.model.Student;
import com.trainingmug.student.app.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentService {
    private StudentRepository repository = new StudentRepository();

    public StudentService() {
        // Adding some test data
        repository.save(new Student(1, "John Doe", 20));
        repository.save(new Student(2, "Jane Smith", 22));
        repository.save(new Student(3, "Mike Johnson", 19));
        repository.save(new Student(4, "James Pomson", 18));
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return repository.findById(id);
    }

    public void addStudent(Student student) {
        repository.save(student);
    }

    public void deleteStudent(int id) {
        repository.deleteById(id);
    }
}
