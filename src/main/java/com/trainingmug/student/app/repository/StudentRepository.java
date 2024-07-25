package com.trainingmug.student.app.repository;


import com.trainingmug.student.app.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private List<Student> students = new ArrayList<>();

    public List<Student> findAll() {
        return students;
    }

    public Optional<Student> findById(int id) {
        return students.stream().filter(student -> student.getId() == id).findFirst();
    }

    public void save(Student student) {
        students.add(student);
    }

    public void deleteById(int id) {
        students.removeIf(student -> student.getId() == id);
    }
}
